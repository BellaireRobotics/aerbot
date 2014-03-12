package com.bellaire.aerbot.listeners;

import com.bellaire.aerbot.Environment;

public class AutonomousListener implements Listener {

  private Environment env;
  private long time;
  private long last;
  

  public void init(Environment env) {
    this.env = env;
  }

  public boolean isComplete() {
    return env.isOperatorControl();
  }

  public boolean shouldExecute() {
    return env.isAutonomous();
  }

  public void execute() {
    if(System.currentTimeMillis() - last > 1000)
      time = System.currentTimeMillis();// start time
    if(System.currentTimeMillis() - time < 3500){
    	// move forward (actually backwards) for 3.5 seconds with straight driving at 0.35 motor speed
      env.getWheelSystem().straightDrive(-0.35);
    }else
      env.getWheelSystem().setMotors(0, 0);
    last = System.currentTimeMillis();
  }

}
