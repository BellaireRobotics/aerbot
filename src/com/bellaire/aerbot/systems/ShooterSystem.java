package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSystem implements RobotSystem {

  private Victor motor;
  private Relay pneumatic;
  private boolean shot;
  private boolean buttonPressed;
  private boolean motorOn;
  private boolean shotPressed;

  public void init(Environment e) {
    motor = new Victor(10);
    pneumatic = new Relay(9);
  }

  public void destroy() {
	  motor.free();
	  pneumatic.free();
  }

  public void shoot(InputMethod input) {
    if(input.getPrepareToShoot() && !buttonPressed){
    	//toggle wheels speed between off and full speed
    	setMotor(motorOn ? 0 : 1);
    }
    buttonPressed = input.getPrepareToShoot();
    
    //toggle shooter pneumatic
    if(!shotPressed && input.getShoot()){
    	if(shot)
    		pneumaticDown();
    	else
    		fire();
    }
    shotPressed = input.getShoot();
    
    try{
    	SmartDashboard.putBoolean("Shooter motor on: ", motorOn);
    	SmartDashboard.putNumber("Shooter motor speed: ", motor.getSpeed());
    	SmartDashboard.putBoolean("Shooter pneumatic on: ", pneumatic.get() != Relay.Value.kOff);
    }catch(NullPointerException ex){
    	
    }
  }
  
  public void setMotor(double speed){
	  motor.set(speed);
	  motorOn = speed == 1;// update the motorOn instance variable
  }
  
  public void fire(){
	  if(motor.get() == 0)
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