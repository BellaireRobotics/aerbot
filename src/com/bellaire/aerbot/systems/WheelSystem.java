package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.custom.RobotDrive3;
import com.bellaire.aerbot.input.InputMethod;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WheelSystem extends PIDSubsystem implements RobotSystem {

    private static final double Kp = 3;
    private static final double Ki = .2;
    private static final double Kd = 0.0;

    private GyroSystem gyro;
    private SonarSystem sonar;
    private AccelerometerSystem accelerometer;
    private RobotDrive3 wheels;
    private Relay gearbox;
    private int gear = 0; // off
    private boolean gearPress = false;
    private Compressor compressor;

    private double currentLeftY = 0, currentRightX = 0;
    private double currentRampY = 0, currentRampX = 0;

    public WheelSystem() {
        super(Kp, Ki, Kd);
    }

    public void init(Environment e) {
        wheels = new RobotDrive3(1, 2);

        wheels.setSafetyEnabled(false);

        this.gyro = e.getGyroSystem();
        sonar = e.getSonarSystem();

        accelerometer = e.getAccelerometerSystem();

        this.sonar = e.getSonarSystem();

        gearbox = new Relay(2);
        this.gearsOff();

        this.compressor = e.getCompressor();
    }

    public void destroy() {
    }

    public void setMotors(double left, double right) {
        wheels.setLeftRightMotorOutputs(left, right);
    }

    public void drive(double outputMaginitude, double curve) {
        wheels.drive(outputMaginitude, curve);
        automaticGearShift();
    }

    public void move(InputMethod input) {
        currentLeftY = -input.getLeftY();
        currentRightX = input.getRightX();

        currentRampY += (currentLeftY - currentRampY) * (20d / 100d);
        currentRampX += (currentRightX - currentRampX) * (20d / 100d);

        /*if(currentLeftY == 0) {
         currentRampY = 0;
         }
         if(currentRightX == 0) {
         currentRampX = 0;
         }*/
        wheels.arcadeDrive(currentRampY, currentRampX);

        //SmartDashboard.putNumber("Sonar Distance", sonar.getDistance());
        //SmartDashboard.putNumber("Robot Heading", motion.getHeading());
        //SmartDashboard.putNumber("Robot Speed", motion.getSpeed());
        if (!input.gearSwitch()) {
            gearPress = false;
        }

        if (gearPress == false) {
            if (input.gearSwitch()) {
                gearPress = true;
                if (gear == 0) {
                    this.gearsForward();
                } else if (gear == 1) {
                    this.gearsOff();
                }
            }
        }

        /*if (input.gearSwitch() && gyro.getHeading() > 2) {
            faceForward();
        }*/
        try {
            SmartDashboard.putBoolean("Low gear: ", gearPress);
            SmartDashboard.putNumber("Angle: ", gyro.getHeading());
            SmartDashboard.putNumber("Range: ", sonar.getDistance());
        } catch (NullPointerException ex) {

        }
    }

    public void automaticGearShift() {
        if (accelerometer.getAccelerationX() > 3) // if encoder rate is greater than gear shift speed
        {
            gearsForward();
        } else {
            gearsOff();
        }
    }

    public void gearsOff() {
        gear = 0;
        gearbox.set(Relay.Value.kOff);
    }

    public void gearsForward() {
        gear = 1;
        gearbox.set(Relay.Value.kReverse);
    }

    public void faceForward() {
        if (gyro.getHeading() < 90 || (gyro.getHeading() < 270 && gyro.getHeading() > 180)) {
            setMotors(.2, -.2);
        } else {
            setMotors(-.2, .2);
        }
    }

    public void driveDistance(double distance) {
        if (!getPIDController().isEnable()) {
            setSetpointRelative(distance);
            enable();
        } else if (getPosition() == distance) {
            disable();
        }
    }

    public void selfCatch() {
        if (gyro.getHeading() > 1 && gyro.getHeading() < 358) {
            faceForward();
        } else if (getPosition() == 0) {
            // if getPosition equals the point in front of the truss
            //shoot
            driveDistance(0);//driveToDistance point behind truss
        } else if (getSetpoint() == 0) {
            driveDistance(0);//drive to point behind the truss
        } else if (getSetpoint() != 0 || getSetpoint() == 0) {
            //if setpoint is not the point in front of the truss OR the setpoint is the point in front of the truss
            driveDistance(0);//drive to point in front of truss
        }
    }

    protected double returnPIDInput() {
        return sonar.getDistance();
    }

    protected void usePIDOutput(double d) {
        setMotors(d, -d);
    }

    protected void initDefaultCommand() {

    }
}
