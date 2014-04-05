package com.bellaire.aerbot.input;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox360Input implements InputMethod {

  private Joystick controller;
	private Joystick partner;

  public static final int BUTTON_A = 1;// intake out
  public static final int BUTTON_B = 2;// gearshift override
  public static final int BUTTON_X = 3; // intake in
  public static final int BUTTON_Y = 4; // switchfront
  public static final int BUTTON_LB = 5;
  public static final int BUTTON_RB = 6; // intake out and intake motors on
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;

  public Xbox360Input() {
    controller = new Joystick(1);
    partner = new Joystick(2);
  }

  public double getLeftX() {
    return controller.getRawAxis(1);
  }

  public double getRightX() {
    return controller.getRawAxis(4);
  }

  public double getLeftY() {
    return controller.getRawAxis(2);
  }

  public double getRightY() {
    return controller.getRawAxis(5);
  }

  public boolean getIntakeIn() {
    return partner.getRawButton(BUTTON_X);
  }

  public boolean getIntakeOut() {
    return partner.getRawButton(BUTTON_A);
  }

  public boolean getIntakePneumatic() {
    return partner.getRawAxis(3) > .2;
  }

  public boolean getShoot() {
    return partner.getRawAxis(3) < 0.2;
  }
  
  public boolean getPrepareToShoot(){
	  return partner.getRawButton(BUTTON_LB);
  }

  public boolean gearSwitch() {
    return controller.getRawButton(BUTTON_B);
  }

  public boolean getLeftTurn() {
    return controller.getRawAxis(6) < -0.1;
  }

  public boolean getRightTurn() {
    return controller.getRawAxis(6) > 0.1;
  }

	public boolean getAutoIntake() {
		return partner.getRawButton(BUTTON_RB);
	}

	public boolean getSwitchFront() {
		return controller.getRawButton(BUTTON_Y);
	}

	public boolean getCatch() {
		return controller.getRawButton(BUTTON_A);
	}

	public boolean getTurnAround() {
		return controller.getRawButton(BUTTON_START);
	}
}