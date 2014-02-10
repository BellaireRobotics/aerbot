package com.bellaire.aerbot;

import com.bellaire.aerbot.input.InputMethod;
import com.bellaire.aerbot.input.JoystickInput;
import com.bellaire.aerbot.input.Xbox360Input;
import com.bellaire.aerbot.systems.AccelerometerSystem;
import com.bellaire.aerbot.systems.GyroSystem;
import com.bellaire.aerbot.systems.IntakeSystem;
import com.bellaire.aerbot.systems.ShooterSystem;
import com.bellaire.aerbot.systems.SonarSystem;
import com.bellaire.aerbot.systems.WheelSystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.RobotBase;

public class Environment {

    private RobotBase robot;

    private InputMethod input;
    private WheelSystem wheels;
    private GyroSystem gyro;
    private IntakeSystem intake;
    private SonarSystem sonar;
    private AccelerometerSystem accelerometer;
    private ShooterSystem shooter;
    private Compressor compressor;

    public Environment(RobotBase robot) {
        this.robot = robot;

        this.input = new Xbox360Input();

        this.gyro = new GyroSystem();
        this.gyro.init(this);

        this.wheels = new WheelSystem();
        this.wheels.init(this);

        this.intake = new IntakeSystem();
        this.intake.init(this);

        this.sonar = new SonarSystem();
        this.sonar.init(this);
        
        this.shooter = new ShooterSystem();
        this.shooter.init(this);

        this.accelerometer = new AccelerometerSystem();
        this.accelerometer.init(this);

        this.compressor = new Compressor(1, 1);
        this.compressor.start();
    }

    public InputMethod getInput() {
        return input;
    }

    public WheelSystem getWheelSystem() {
        return wheels;
    }

    public GyroSystem getGyroSystem() {
        return gyro;
    }

    public IntakeSystem getIntakeSystem() {
        return intake;
    }

    public SonarSystem getSonarSystem() {
        return sonar;
    }

    public AccelerometerSystem getAccelerometerSystem() {
        return accelerometer;
    }
    
    public ShooterSystem getShooterSystem(){
        return shooter;
    }
    
    public Compressor getCompressor(){
        return compressor;
    }
    
    public boolean isAutonomous() {
        return robot.isAutonomous();
    }

    public boolean isOperatorControl() {
        return robot.isOperatorControl();
    }
}
