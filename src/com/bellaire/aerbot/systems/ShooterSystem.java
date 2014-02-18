package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSystem implements RobotSystem {

    private Jaguar jaguar;
    
    private long start = 0, end = 0;
    
    public void init(Environment e) {
        jaguar = new Jaguar(10);
        
        SmartDashboard.putNumber("shooter interval", 0.3d);
        SmartDashboard.putNumber("shooter power", 0.3d);
    }

    public void destroy() {
        
    }
    
    public void shoot(InputMethod input) {
        SmartDashboard.putNumber("shooter motor", jaguar.get());
        if(input.getShoot() && start == -1) {
            start = System.currentTimeMillis();
            jaguar.set(-SmartDashboard.getNumber("shooter power", 0.3d));
        }
        
        if(input.getAntiShoot()) {
            jaguar.set(0.2d);
        }
        
        long cur = System.currentTimeMillis();
        if(cur - start > (1000 * SmartDashboard.getNumber("shooter interval", 3d)) && start != -1) {
            start = -1;
            jaguar.set(0d);
        }
    }
    
}