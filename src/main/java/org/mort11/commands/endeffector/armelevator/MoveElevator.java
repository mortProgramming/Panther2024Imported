package org.mort11.commands.endeffector.armelevator;

import org.mort11.subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.Command;

public class MoveElevator extends Command {
	private Elevator elevator;
	private double increment;

	public MoveElevator(double increment) {
		this.increment = increment;
		elevator = Elevator.getInstance();
		addRequirements(elevator);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		elevator.setSetpoint(elevator.getSetpoint() + increment);
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
