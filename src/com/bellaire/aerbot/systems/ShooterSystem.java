package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ShooterSystem extends PIDSubsystem implements RobotSystem {

    private static final double Kp = 6;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    public static final double LAUNCH_SPEED = 3;
    public static final double GRAVITY = 9.81;
    public static final double GOAL_HEIGHT = 3;
    public static final double POT_DOWN = 3;
    private Environment env;
    private AnalogChannel pot;
    private Jaguar motors;

    public ShooterSystem() {
        super(Kp, Ki, Kd);
    }

    public void init(Environment e) {
        env = e;
        pot = new AnalogChannel(3);
        motors = new Jaguar(10);
    }

    public void destroy() {
        pot.free();
        motors.free();
    }

    public void shoot(double distance) {
        double angle = 0;
        while (pot.getVoltage() < angle / 72) {
            motors.set(1);
        }
        motors.set(0);
        setSetpoint(POT_DOWN);
        enable();
    }
    
    public void fire(InputMethod input){
        if(input.getShoot())
            motors.set(.2);
        else
            motors.set(0);
    }

    protected double returnPIDInput() {
        return pot.getVoltage();
    }

    protected void usePIDOutput(double d) {
        motors.set(d);
    }

    protected void initDefaultCommand() {

    }

}
