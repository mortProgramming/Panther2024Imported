package org.mort11.commands.endeffector.clawwrist;

import org.mort11.subsystems.Wrist;

import edu.wpi.first.wpilibj2.command.Command;

public class SetWrist extends Command {
	private Wrist wrist;
	private double setpoint;

	public SetWrist(double setpoint) {
		this.setpoint = setpoint;
		wrist = Wrist.getInstance();
		addRequirements(wrist);
	}

	@Override
	public void initialize() {
		wrist.setSetpoint(setpoint);
	}

	@Override
	public void execute() {
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return wrist.nearSetpoint();
	}
}
