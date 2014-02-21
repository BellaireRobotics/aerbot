package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class RoboRealmSystem extends PIDSubsystem implements RobotSystem{

    private static final double Kp = 1.1;
    private static final double Ki = .2;
    private static final double Kd = 0;
    
    private NetworkTable XTable;
    private NetworkTable YTable;

    private final TableListenerX XListener = new TableListenerX();
    private final TableListenerY YListener = new TableListenerY();

    private double centerX;
    private double centerY;
    
    private Environment environment;
    
    public RoboRealmSystem(){
        super(Kp, Ki, Kd);
    }

    public void init(Environment e) {
        environment = e;
        XTable = NetworkTable.getTable("VisionTableX");
        XTable.addTableListener(XListener);
        YTable = NetworkTable.getTable("VisionTableY");
        YTable.addTableListener(YListener);
    }

    public void destroy() {

    }
    
    public void faceHotTarget(){
        setSetpoint(0);
        enable();
    }

    protected double returnPIDInput() {
        return centerX;
    }

    protected void usePIDOutput(double d) {
        environment.getWheelSystem().setMotors(-d, d);
    }

    protected void initDefaultCommand() {
    }

    private class TableListenerX implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
            try{
                centerX = Double.parseDouble(obj.toString());
            }catch(NumberFormatException ex){
                
            }
        }
    }

    private class TableListenerY implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
            try{
                centerY = Double.parseDouble(obj.toString());
            }catch(NumberFormatException ex){
                
            }
        }
    }
}
