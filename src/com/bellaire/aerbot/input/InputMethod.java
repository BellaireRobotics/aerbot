package com.bellaire.aerbot.input;

public interface InputMethod {

    public double getLeftX();

    public double getRightX();

    public double getLeftY();

    public double getRightY();

    public boolean getIntakeIn();

    public boolean getIntakeOut();

    public boolean getIntakePneumatic();

    public boolean getShoot();

    public boolean gearSwitch();
    
    public boolean getAntiShoot();
}
