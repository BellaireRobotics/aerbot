package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class RoboRealmSystem implements RobotSystem {

    private NetworkTable XTable;
    private NetworkTable YTable;

    private final TableListenerX XListener = new TableListenerX();
    private final TableListenerY YListener = new TableListenerY();

    public RoboRealmSystem() {

    }

    public void init(Environment e) {
        XTable = NetworkTable.getTable("VisionTableX");
        XTable.addTableListener(XListener);
        YTable = NetworkTable.getTable("VisionTableY");
        YTable.addTableListener(YListener);
    }

    public void destroy() {

    }

    private class TableListenerX implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
        }
    }

    private class TableListenerY implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
        }
    }
}
