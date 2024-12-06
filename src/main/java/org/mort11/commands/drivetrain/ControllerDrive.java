package org.mort11.commands.drivetrain;

import java.util.function.DoubleSupplier;

import org.mort11.subsystems.Drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

public class ControllerDrive extends Command {
	private Drivetrain drivetrain;

	private final DoubleSupplier translationXSupplier;
	private final DoubleSupplier translationYSupplier;
	private final DoubleSupplier rotationSupplier;

	private boolean button;

	/**
	 * @param translationXSupplier
	 *            Supplier for x-axis movement
	 * @param translationYSupplier
	 *            Supplier for y-axis movement
	 * @param rotationSupplier
	 *            Supplier for rotational movement
	 * @param button
	 *            Whether field-oriented drive is used
	 */
	public ControllerDrive(DoubleSupplier translationXSupplier, DoubleSupplier translationYSupplier,
			DoubleSupplier rotationSupplier, boolean button) {
		drivetrain = Drivetrain.getInstance();

		this.translationXSupplier = translationXSupplier;
		this.translationYSupplier = translationYSupplier;
		this.rotationSupplier = rotationSupplier;

		this.button = button;

		addRequirements(drivetrain);
	}

	/**
	 * Assumes robot-oriented drive
	 *
	 * @param translationXSupplier
	 *            Supplier for x-axis movement
	 * @param translationYSupplier
	 *            Supplier for y-axis movement
	 * @param rotationSupplier
	 *            Supplier for rotational movement
	 */
	public ControllerDrive(DoubleSupplier translationXSupplier, DoubleSupplier translationYSupplier,
			DoubleSupplier rotationSupplier) {
		this(translationXSupplier, translationYSupplier, rotationSupplier, false);
	}

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {
		if (button) {
			drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(translationXSupplier.getAsDouble() * 0.2,
					translationYSupplier.getAsDouble() *0.2, rotationSupplier.getAsDouble() * 0.2,
					drivetrain.getGyroscopeRotation()));
		} else {
			drivetrain.drive(new ChassisSpeeds(translationXSupplier.getAsDouble(), translationYSupplier.getAsDouble(),
					rotationSupplier.getAsDouble()));
		}

	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(boolean interrupted) {
		drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
	}
}
