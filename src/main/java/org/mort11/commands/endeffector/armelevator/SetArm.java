package org.mort11.commands.endeffector.armelevator;

import org.mort11.subsystems.Arm;

import edu.wpi.first.wpilibj2.command.Command;

public class SetArm extends Command {
	private Arm arm;
	private double setpoint;

	public SetArm(double setpoint) {
		this.setpoint = setpoint;
		arm = Arm.getInstance();
		addRequirements(arm);
	}

	@Override
	public void initialize() {
		arm.setSetpoint(setpoint);
	}

	@Override
	public void execute() {
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return arm.nearSetpoint();
	}
}
