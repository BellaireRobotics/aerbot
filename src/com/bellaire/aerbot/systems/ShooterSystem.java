package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

public class ShooterSystem implements RobotSystem {

  private Jaguar jaguar;
  private Relay pneumatic;
  private boolean shot;
  private boolean buttonPressed;
  private boolean motorOn;

  public void init(Environment e) {
    jaguar = new Jaguar(10);
    pneumatic = new Relay(3);
  }

  public void destroy() {
	  jaguar.free();
	  pneumatic.free();
  }

  public void shoot(InputMethod input) {
    if(input.getPrepareToShoot() && !buttonPressed){
    	//toggle wheels speed between off and full speed
    	setMotor(motorOn ? 0 : 1);
    }
    buttonPressed = input.getPrepareToShoot();
    if(input.getShoot())
    	fire();
    else
    	pneumaticDown();
  }
  
  public void setMotor(double speed){
	  jaguar.set(speed);
	  motorOn = speed == 1;// update the motorOn instance variable
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