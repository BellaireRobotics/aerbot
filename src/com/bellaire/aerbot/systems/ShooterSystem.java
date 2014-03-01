package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class ShooterSystem implements RobotSystem {

	public static final double SHOT_DELAY = 2.5;
	
  private Jaguar jaguar;
  private Relay pneumatic;
  private boolean shot;
  private Timer timer;

  public void init(Environment e) {
    jaguar = new Jaguar(10);
    pneumatic = new Relay(3);
    timer = new Timer();
    timer.start();
  }

  public void destroy() {
	  jaguar.free();
	  pneumatic.free();
  }

  public void shoot(InputMethod input) {
    if(input.getPrepareToShoot()){
    	setMotor(1);
    }else{
    	setMotor(0.5);
    	timer.reset();
    }
    // shoot button won't respond until a certain amount of time after motors speed up
    if(input.getShoot() && timer.get() > SHOT_DELAY)
    	fire();
    else
    	pneumaticDown();
  }
  
  public void setMotor(double speed){
	  jaguar.set(speed);;
  }
  
  public void fire(){
	  if(jaguar.get() == 0)
		  setMotor(1);// just in case motor is not moving
	  if(!shot){
		  pneumatic.set(Relay.Value.kForward);
		  shot = true;
	  }
  }
  
  public void pneumaticDown(){
	  if(shot){
		  pneumatic.set(Relay.Value.kOff);
		  shot = false;
	  }
  }

}