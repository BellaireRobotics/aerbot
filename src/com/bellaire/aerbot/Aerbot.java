/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.bellaire.aerbot;

import com.bellaire.aerbot.controllers.AutonomousController;
import com.bellaire.aerbot.controllers.OperatorController;
import com.bellaire.aerbot.systems.CameraSystem;
import edu.wpi.first.wpilibj.image.NIVisionException;
import com.bellaire.aerbot.Exceptions.HotTargetNotFoundException;
import edu.wpi.first.wpilibj.IterativeRobot;
import com.bellaire.aerbot.input.InputMethod;
import com.bellaire.aerbot.Exceptions.HotTargetNotFoundException;

public class Aerbot extends IterativeRobot {

    private Environment environment;
    private Executer exec;

    private AutonomousController autonomous;
    private OperatorController operator;

    public void robotInit() {
        this.environment = new Environment(this);
        this.exec = new Executer(environment);
    }

    public void autonomousInit() {
        exec.onAutonomous();
    }

    private int lastX;

    public void autonomousPeriodic() {
        double speed;
        CameraSystem camera = environment.getCameraSystem();
        double xCoord = camera.getXCoordinate();
        try{
        speed = xCoord - lastX;
        speed = speed / 480;
        environment.getWheelSystem().move(speed, speed);
        lastX = xCoord;
        }catch(HotTargetNotFoundException e){}
        catch(NIVisionException e){}
    }

    public void teleopInit() {
        exec.onTeleop();
        operator = new OperatorController(environment, exec);
    }

    public void teleopPeriodic() {
        operator.update();
    }

    public void testPeriodic() {

    }

}
