package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class RoboRealmSystem implements RobotSystem {

    private NetworkTable nt1;
    private NetworkTable nt2;
    private NetworkTable nt3;

    private final TableListener1 tableListener1 = new TableListener1();
    private final TableListener2 tableListener2 = new TableListener2();
    private final TableListener3 tableListener3 = new TableListener3();

    public RoboRealmSystem() {

    }

    public void init(Environment e) {
        nt1 = NetworkTable.getTable("VisionTable");
        nt1.addTableListener(tableListener1);
        nt2 = NetworkTable.getTable("VisionTable2");
        nt2.addTableListener(tableListener2);
        nt3 = NetworkTable.getTable("VisionTable3");
        nt3.addTableListener(tableListener3);
    }

    public void destroy() {

    }

    private class TableListener1 implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
        }
    }

    private class TableListener2 implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
        }
    }

    private class TableListener3 implements ITableListener {

        public void valueChanged(ITable itable, String key, Object obj, boolean isNew) {
            SmartDashboard.putString(key, obj.toString());
        }
    }
}
