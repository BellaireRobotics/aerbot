package com.bellaire.aerbot.input;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput implements InputMethod {

    private Joystick left, right;

    public JoystickInput() {
        left = new Joystick(1);
        right = new Joystick(2);
    }

    public double getLeftY() {
        return left.getY();
    }

    public double getRightY() {
        return right.getY();
    }

    public double getLeftX() {
        return left.getY();
    }

    public double getRightX() {
        return right.getY();
    }

    public boolean getIntakeIn() {
        return right.getRawButton(4);
    }

    public boolean getIntakeOut() {
        return right.getRawButton(2);
    }

    public boolean getIntakePneumatic() {
        return left.getRawButton(3);
    }

    public boolean getShoot() {
        return right.getTrigger();
    }

    public boolean gearSwitch() {
        return false;
    }
    
    public boolean getAntiShoot(){
        return false;
    }

}
