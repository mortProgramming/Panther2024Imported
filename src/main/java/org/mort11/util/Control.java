package org.mort11.util;

import static org.mort11.util.Constants.ControlPorts.*;
import static org.mort11.util.Constants.RobotSpecs.*;

import java.util.Optional;

import org.mort11.commands.drivetrain.*;
import org.mort11.commands.endeffector.armelevator.MoveArm;
import org.mort11.commands.endeffector.armelevator.MoveElevator;
import org.mort11.commands.endeffector.armelevator.SetArmAndElevator;
import org.mort11.commands.endeffector.clawwrist.Clawtake;
import org.mort11.commands.endeffector.clawwrist.ClawtakeDefault;
import org.mort11.commands.endeffector.clawwrist.SetWrist;
import org.mort11.commands.endeffector.clawwrist.ToggleClawPiston;
import org.mort11.commands.endeffector.floortake.FloorIntake;
import org.mort11.commands.endeffector.floortake.Spit;
import org.mort11.commands.endeffector.floortake.Stow;
import org.mort11.subsystems.Claw;
import org.mort11.subsystems.Drivetrain;
import org.mort11.subsystems.Elevator;
import org.mort11.subsystems.Floortake;
import org.mort11.subsystems.Wrist;
import org.mort11.subsystems.Arm;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Control {
	private static CommandJoystick joystick;
	private static CommandXboxController driverController, xboxController;

	private static Drivetrain drivetrain;
	private static Arm arm;
	private static Claw claw;
	private static Elevator elevator;
	private static Wrist wrist;
	private static Floortake floortake;

	/**
	 * Initialize controllers
	 */
	public static void init() {
		joystick = new CommandJoystick(JOYSTICK);
		driverController = new CommandXboxController(THROTTLE);
		xboxController = new CommandXboxController(XBOX_CONTROLLER);

		joystick.setThrottleChannel(2);
		joystick.setTwistChannel(3);

		drivetrain = Drivetrain.getInstance();
		arm = Arm.getInstance();
		claw = Claw.getInstance();
		elevator = Elevator.getInstance();
		wrist = Wrist.getInstance();
		floortake = Floortake.getInstance();

		SmartDashboard.putBoolean("FastSpeed", false);
	}

	/**
	 * Configure default commands and button bindings
	 */
	public static void configure() {
		
		floortake.setDefaultCommand(new Stow());
		// claw.setDefaultCommand(new ClawtakeDefault());

		joystick.button(1).onTrue(new InstantCommand(drivetrain::zeroGyroscope));

		// joystick.button(2).whileTrue(new SequentialCommandGroup(
		// new MoveToPos(0, Units.inchesToMeters(30), 0), new MoveToPos(-0.5, 0, 0)));
		Optional<Alliance> alliance = DriverStation.getAlliance();

		// joystick.button(5).whileTrue(new MoveToAprilTag(DriverStation.getAlliance().get() == Alliance.Blue ? 6 : 3
		// // 6
		// ));
		// joystick.button(3).whileTrue(new MoveToAprilTag(DriverStation.getAlliance().get() == Alliance.Blue ? 7 : 2
		// // 7
		// ));
		// joystick.button(4).whileTrue(new MoveToAprilTag(DriverStation.getAlliance().get() == Alliance.Blue ? 8 : 1
		// // 8
		// ));
		// DriverStation.getAlliance();


		joystick.button(2).whileTrue(new MoveToTape());

		// joystick.button(6).whileTrue(new SequentialCommandGroup(
		// new MoveToPos(0, Units.inchesToMeters(-31), 0), new MoveToPos(-0.5, 0, 0)));

		joystick.button(9).whileTrue(new MoveToPos(0, 0, 180));

		joystick.povRight().whileTrue(new RotateToAngle(-90, false));
		joystick.povUp().whileTrue(new RotateToAngle(0, false));
		joystick.povLeft().whileTrue(new RotateToAngle(90, false));
		joystick.povDown().whileTrue(new RotateToAngle(180, false));

		// ee
		//xboxController.a().onTrue(SetArmAndElevator.floor());
		xboxController.b().onTrue(SetArmAndElevator.rest());
		xboxController.x().onTrue(SetArmAndElevator.middleNode());
		xboxController.y().onTrue(SetArmAndElevator.upperNode());
		xboxController.start().onTrue(SetArmAndElevator.shelf());
		// xboxController.back().onTrue(SetArmAndElevator.zero());
		// xboxController.axisGreaterThan(3, 0.5).onTrue(SetArmAndElevator.clamp());

		xboxController.leftBumper().toggleOnTrue(Commands.startEnd(() -> SmartDashboard.putBoolean("FastSpeed", false),
				() -> SmartDashboard.putBoolean("FastSpeed", true)));

		// xboxController.leftBumper()
		// .onTrue(new InstantCommand(() ->
		// xboxController.getHID().setRumble(RumbleType.kBothRumble, 1)));
		// xboxController.leftBumper()
		// .onFalse(new InstantCommand(() ->
		// xboxController.getHID().setRumble(RumbleType.kBothRumble, 0)));
		xboxController.leftBumper().onTrue(setRumble(1));
		xboxController.leftBumper().onFalse(setRumble(0));

		// xboxController.rightBumper().onTrue(new InstantCommand(claw::togglePiston,
		// claw));
		xboxController.rightBumper().onTrue(new ToggleClawPiston());

		xboxController.axisLessThan(1, -0.5).whileTrue(new Clawtake(true));
		xboxController.axisGreaterThan(1, 0.5).whileTrue(new Clawtake(false));

		xboxController.leftTrigger().whileTrue(new FloorIntake());
		xboxController.rightTrigger().whileTrue(new Spit());
		// xboxController.rightTrigger(0.66).whileTrue(new Bump());
		// xboxController.rightTrigger(0.33).and(xboxController.rightTrigger(0.66).negate()).whileTrue(new
		// Spit());

		// xboxController.povRight().onTrue(new InstantCommand(() ->
		// wrist.setSetpoint(Constants.Wrist.RIGHT_POSITION)));
		// xboxController.povUp().onTrue(new InstantCommand(() ->
		// wrist.setSetpoint(Constants.Wrist.UP_POSITION)));
		// xboxController.povLeft().onTrue(new InstantCommand(() ->
		// wrist.setSetpoint(Constants.Wrist.LEFT_POSITION)));
		// xboxController.povDown().onTrue(new InstantCommand(() ->
		// wrist.setSetpoint(Constants.Wrist.DOWN_POSITION)));

		// xboxController.povRight().onTrue(new SetWrist(Constants.Wrist.RIGHT_POSITION));
		// xboxController.povUp().onTrue(new SetWrist(Constants.Wrist.UP_POSITION));
		// xboxController.povLeft().onTrue(new SetWrist(Constants.Wrist.LEFT_POSITION));
		// xboxController.povDown().onTrue(new SetWrist(Constants.Wrist.DOWN_POSITION));

		// xboxController.axisLessThan(4, -0.5).whileTrue(new MoveArm(1));
		// xboxController.axisGreaterThan(4, 0.5).whileTrue(new MoveArm(-1));

		// xboxController.axisLessThan(5, -0.5).whileTrue(new MoveElevator(0.2));
		// xboxController.axisGreaterThan(5, 0.5).whileTrue(new MoveElevator(-0.2));
	}

	public static void configureJoystick() {
		drivetrain.setDefaultCommand(
			new Drive(Control::getJoystickY, Control::getJoystickX, Control::getJoystickTwist, true)
		);
	}

	public static void configureController() {
		drivetrain.setDefaultCommand(
			new Drive(Control::getControllerY, Control::getControllerX, Control::getControllerTwist, true)
		);
	}

	/**
	 * Calculate value based on deadband
	 *
	 * @param value
	 * @param deadband
	 */
	private static double deadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	/**
	 * Change joystick axis
	 *
	 * @param value
	 * @param throttleValue
	 *                      from [-1, 1]
	 */
	private static double modifyJoystickAxis(double value, double throttleValue)
	{
	// Deadband
		value = deadband(value, 0.05);

	// Square the axis
		value = Math.copySign(value * value, value);

		throttleValue = (throttleValue + 1) / 2;

	// takes the throttle value and takes it from [-1, 1] to [0.3, 1], and
	// multiplies it by the value
	// return value * (throttleValue * -0.4 + 0.6); // before with [0.2, 1]
		return value * (throttleValue * 0.8 + 0.2);
	// return value * Math.pow(throttleValue * -0.35 + 0.65, 2);
	}
	private static double modifyTwistAxis(double value, double throttleValue)
	{
		value = deadband(value, 0.03);
		value = Math.copySign(value * value, value);
		throttleValue = (throttleValue + 1) / 2;
		return value * (throttleValue * 0.8 + 0.2);
		//Changed thingy to 1 not 0.6
	}

	// public static double modifyJoystickAxis(double value, double throttleValue) {
	// 	value = deadband(value, 0.1);

	// 	throttleValue = -(throttleValue + 1) / 2;

	// 	return Math.copySign(value * value, value) * (throttleValue * (0.8 - 0.1) + 0.1);
	// }

	public static double getJoystickX() {
		return -(modifyJoystickAxis(joystick.getX(), -joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND);
	}

	public static double getJoystickY() {
		return -(modifyJoystickAxis(joystick.getY(), -joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND);
	}

	public static double getJoystickTwist() {
		return (modifyJoystickAxis(joystick.getTwist(), -joystick.getThrottle())
				* MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND);
	}

	public static double getControllerX() {
		return -(modifyJoystickAxis(driverController.getLeftX(), -joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND);
	}

	public static double getControllerY() {
		return -(modifyJoystickAxis(driverController.getLeftY(), -joystick.getThrottle()) * MAX_VELOCITY_METERS_PER_SECOND);
	}

	public static double getControllerTwist() {
		return (modifyTwistAxis(driverController.getRightX(), -joystick.getThrottle())
				* MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND);
	}

	// public static double getJoystickX() {
	// 	return -(modifyJoystickAxis(joystick.getX(), throttle.getRawAxis(2)) * MAX_VELOCITY_METERS_PER_SECOND);
	// }

	// public static double getJoystickY() {
	// 	return -(modifyJoystickAxis(joystick.getY(), throttle.getRawAxis(2)) * MAX_VELOCITY_METERS_PER_SECOND);
	// }

	// public static double getJoystickTwist() {
	// 	return (modifyTwistAxis(joystick.getTwist(), throttle.getRawAxis(2))
	// 			* MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND);
	// }

	public static void setControllerRumble(double value) {
		xboxController.getHID().setRumble(RumbleType.kBothRumble, value);
	}

	public static Command setRumble(double value) {
		return new InstantCommand(() -> xboxController.getHID().setRumble(RumbleType.kBothRumble, value));
	}

}
