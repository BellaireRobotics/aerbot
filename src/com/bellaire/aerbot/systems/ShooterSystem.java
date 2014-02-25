package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSystem implements RobotSystem {

  private Jaguar jaguar;
  private Relay pneumatic;
  private boolean shot;

  public void init(Environment e) {
    jaguar = new Jaguar(10);
  }

  public void destroy() {

  }

  public void shoot(InputMethod input) {
    if(input.getPrepareToShoot())
    	setMotor(1);
    else
    	setMotor(0);
    if(input.getShoot())
    	fire();
    else
    	pneumaticDown();
  }
  
  public void setMotor(int speed){
	  jaguar.set(speed);
  }
  
  public void fire(){
	  if(jaguar.get() == 0)
		  setMotor(1);
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