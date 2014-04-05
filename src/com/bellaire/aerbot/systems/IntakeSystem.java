package com.bellaire.aerbot.systems;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.input.InputMethod;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Jaguar;

public class IntakeSystem implements RobotSystem {

	private Jaguar motor;
	private Relay arm;
	private boolean armPress;
	private boolean armDown;
	private boolean autoPress;

	public void init(Environment e) {
		motor = new Jaguar(7);
		arm = new Relay(5);
		arm.set(Relay.Value.kOff);
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
		} else if (input.getAutoIntake()) {
			motor.set(1);// intake motor on
		} else {
			motor.set(0);
		}

		// let the pneumatic down
		if(input.getCatch()){
			arm.set(Relay.Value.kForward);
			armDown = true;
		}else if (input.getIntakePneumatic() && !armPress) {
			arm.set(armDown ? Relay.Value.kReverse : Relay.Value.kForward);
			armDown = !armDown;
		} else if (!autoPress && input.getAutoIntake()) {
			arm.set(Relay.Value.kForward);
			armDown = true;
		} else if (autoPress && !input.getAutoIntake()) {
			// raise the pneumatic when the button is released
			arm.set(Relay.Value.kReverse);
			armDown = false;
		}
		armPress = input.getIntakePneumatic();
		autoPress = input.getAutoIntake();
	}
}
