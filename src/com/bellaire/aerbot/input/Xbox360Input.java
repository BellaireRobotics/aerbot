package com.bellaire.aerbot.input;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox360Input implements InputMethod {

    private Joystick controller;
    
    public static final int BUTTON_A = 1;// intake out
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3; // intake in
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_LB = 5;
    public static final int BUTTON_RB = 6; // intake pneumatic
    public static final int BUTTON_BACK = 7; // not to be used
    public static final int BUTTON_START = 8; // not to be used
    
    public Xbox360Input() {
        controller = new Joystick(1);
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
        return controller.getRawButton(BUTTON_X);
    }

    public boolean getIntakeOut() {
        return controller.getRawButton(BUTTON_A);
    }

    public boolean getIntakePneumatic() {
        return controller.getRawButton(BUTTON_LB);
    }

    public boolean getShoot() {
        return controller.getRawAxis(3) < 0;
    }

    public boolean gearSwitch() {
        return controller.getRawButton(BUTTON_B);
    }
    
    public boolean getAntiShoot(){
        return controller.getRawAxis(3) > 0;
    }
}
