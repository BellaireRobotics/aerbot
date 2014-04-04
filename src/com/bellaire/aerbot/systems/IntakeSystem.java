package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Jaguar;

public class IntakeSystem implements RobotSystem {

  private Jaguar motor;
  private Relay arm;
  private boolean armPress;
  private boolean armDown;
  private boolean autoPress;

  public void init(Environment e) {
    motor = new Jaguar(4);
    arm = new Relay(4);
  }

  public void destroy() {
    motor.free();
    arm.free();
  }

  public void intake(InputMethod input) {
    if (input.getIntakeIn()) {
      motor.set(-1);
    } else if (input.getIntakeOut()) {
      motor.set(1);
    } else if(input.getAutoIntake()){
    	motor.set(1);// intake motor on
    } else {
      motor.set(0);
    }

    if (!input.getIntakePneumatic()) {
      armPress = false;
      
      // let the pneumatic down
      if(!autoPress && input.getAutoIntake()){
      	arm.set(Relay.Value.kForward);
      	armDown = true;
      }else if(autoPress && !input.getAutoIntake()){
      	// raise the pneumatic when the button is released
      	arm.set(Relay.Value.kOff);
      	armDown = false;
      }
      
    } else if (!armPress) {
      armPress = true;

      if (armDown) {
        arm.set(Relay.Value.kForward); // when LB is pressed pneumatic piston turns on or off
      } else {
        arm.set(Relay.Value.kOff);
      }

      armDown = !armDown;
    }
    autoPress = input.getAutoIntake();
  }
}
