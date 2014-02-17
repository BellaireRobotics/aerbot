package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

public class IntakeSystem implements RobotSystem {
    
    private Victor motor;
    private Relay arm;
    private boolean armPress;
    private boolean armDown;
    
    public void init(Environment e) {
        motor = new Victor(4);
        arm = new Relay(3);
    }
    
    public void destroy() {
        motor.free();
        arm.free();
    }
    
    public void intake(InputMethod input) {
        if (input.getIntakeIn()) {
            motor.set(1);
        } else if (input.getIntakeOut()) {
            motor.set(-1);
        } else {
            motor.set(0);
        }
        if(!input.getIntakePneumatic())
            armPress = false;
        else if (!armPress) {
            armPress = true;
            if (armDown) {
                arm.set(Relay.Value.kForward); // when LB is pressed pneumatic piston turns on or off
            } else {
                arm.set(Relay.Value.kOff);
            }
            armDown = !armDown;
        }
    }
}
