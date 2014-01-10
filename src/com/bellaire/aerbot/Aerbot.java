package com.bellaire.aerbot;

import com.bellaire.aerbot.controllers.AutonomousController;
import com.bellaire.aerbot.controllers.OperatorController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Aerbot extends IterativeRobot {

    private Environment environment;
    private Executer exec;

    private AutonomousController autonomous;
    private OperatorController operator;
    
    private Compressor compressor;


    public void robotInit() {
        this.environment = new Environment(this);
        this.exec = new Executer(environment);
        this.compressor = new Compressor(1, 1);
        this.compressor.start();
    }

    public void autonomousInit() {
        exec.onAutonomous();
        autonomous = new AutonomousController(environment, exec);
    }

    public void autonomousPeriodic() {
        //autonomous.update();
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

    public void disabledInit() {

    }

    public void disabledPeriodic() {

    }

}
