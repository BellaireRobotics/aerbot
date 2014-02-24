package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class RoboRealmSystem implements RobotSystem{
	
    private NetworkTable XTable;
    private NetworkTable YTable;

    private final TableListenerX XListener = new TableListenerX();
    private final TableListenerY YListener = new TableListenerY();

    private double centerX;
    private double centerY;
    
    private Environment environment;

    public void init(Environment e) {
        environment = e;
        XTable = NetworkTable.getTable("VisionTableX");
        XTable.addTableListener(XListener);
        YTable = NetworkTable.getTable("VisionTableY");
        YTable.addTableListener(YListener);
    }

    public void destroy() {

    }
    
    //roborealm should say none if the bot can't see a hot target
    public boolean onHotSide(){
    	return centerX != -1;
    }

    private class TableListenerX implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
            try{
                centerX = Double.parseDouble(obj.toString());
            }catch(NumberFormatException ex){
                if(obj.toString().equals("none"))
                	centerX = -1;
            }
        }
    }

    private class TableListenerY implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
            try{
                centerY = Double.parseDouble(obj.toString());
            }catch(NumberFormatException ex){
                if(obj.toString().equals("none"))
                	centerY = -1;
            }
        }
    }
}
