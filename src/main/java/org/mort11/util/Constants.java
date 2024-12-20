package org.mort11.util;

import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public final class Constants {
	public final static int PCM = 26;

	public final static class ControlPorts {
		public final static int JOYSTICK = 0;
		public final static int THROTTLE = 1;
		public final static int XBOX_CONTROLLER = 2;
	}

	public final static class Drivetrain {
		public final static int FRONT_LEFT_DRIVE = 5;
		public final static int FRONT_LEFT_STEER = 6;
		public final static int FRONT_LEFT_STEER_ENCODER = 32;
		public final static double FRONT_LEFT_STEER_OFFSET = -Math.toRadians(252 + 180);

		public final static int FRONT_RIGHT_DRIVE = 3;
		public final static int FRONT_RIGHT_STEER = 4;
		public final static int FRONT_RIGHT_STEER_ENCODER = 33;
		public final static double FRONT_RIGHT_STEER_OFFSET = -Math.toRadians(202 + 180);

		public final static int BACK_LEFT_DRIVE = 2;
		public final static int BACK_LEFT_STEER = 1;
		public final static int BACK_LEFT_STEER_ENCODER = 30;
		public final static double BACK_LEFT_STEER_OFFSET = -Math.toRadians(140 + 180);

		public final static int BACK_RIGHT_DRIVE = 18;
		public final static int BACK_RIGHT_STEER = 19;
		public final static int BACK_RIGHT_STEER_ENCODER = 31;
		public final static double BACK_RIGHT_STEER_OFFSET = -Math.toRadians(42 + 180);

		public final static double ROTATE_TO_ANGLE_KP = 0.1;
		public final static double ROTATE_TO_ANGLE_KI = 0;
		public final static double ROTATE_TO_ANGLE_KD = 0.01;
		public final static double ROTATE_TO_ANGLE_TOLERANCE = 1;

		public final static double BALANCE_KP = 0.029; // 0.025
		public final static double BALANCE_KI = 0.0; // 0.0000
		public final static double BALANCE_KD = 0.005; // 0.000
		public final static double BALANCE_TOLERANCE = 0.25;

		// TODO: tune pid
		public final static double ATX_KP = 1.1;
		public final static double ATX_KI = 0;
		public final static double ATX_KD = 0;
		public final static double ATX_TOLERANCE = 0.01;

		// TODO: tune pid
		public final static double ATY_KP = 1.1;
		public final static double ATY_KI = 0;
		public final static double ATY_KD = 0;
		public final static double ATY_TOLERANCE = 0.05;

		// TODO: tune pid
		public final static double ATOMEGA_KP = 0.02;
		public final static double ATOMEGA_KI = 0;
		public final static double ATOMEGA_KD = 0;
		public final static double ATOMEGA_TOLERANCE = 0.05;

		public final static double ODOMX_KP = 3;
		public final static double ODOMX_KI = 0;
		public final static double ODOMX_KD = 0;
		public final static double ODOMX_TOLERANCE = 0.1;

		public final static double ODOMY_KP = 3;
		public final static double ODOMY_KI = 0.0;
		public final static double ODOMY_KD = 0;
		public final static double ODOMY_TOLERANCE = 0.1;

		public final static double TAPEX_KP = 0.1;
		public final static double TAPEX_KI = 0.01;
		public final static double TAPEX_KD = 0.00;
		public final static double TAPEXTOLERANCE = 0.5;

		public final static double TAPEY_KP = 0.1;
		public final static double TAPEY_KI = 0.01;
		public final static double TAPEY_KD = 0.00;
		public final static double TAPEY_TOLERANCE = 0.5;

		public final static double ODOMOMEGA_KP = 0.1;
		public final static double ODOMOMEGA_KI = 0;
		public final static double ODOMOMEGA_KD = 0.01;
		public final static double ODOMOMEGA_TOLERANCE = 1;

	}

	public final static class Elevator {
		public final static int ELEVATOR_MASTER = 7;
		public final static int ELEVATOR_FOLLOWER = 8;
		public final static double ELEVATOR_SPEED = .5;

		// public final static double MAX_VELOCITY = 250;
		// public final static double MAX_ACCELERATION = 310;
		public final static double MAX_VELOCITY = 50;
		public final static double MAX_ACCELERATION = 100;

		public final static double KP = 1.4; // 2.1
		public final static double KI = 0;
		public final static double KD = 0.1;

		public final static double KS = 0.076478;
		public final static double KV = 1.4185;
		public final static double KA = 0.0882;
		public final static double KG = 0.2731;

		// public final static int LIMIT_SWITCH = 4;

		// public final static double FLOOR_POSITION = 0.2;
		// public final static double SHELF_POSITION = 56; // 59
		// public final static double MIDDLE_NODE_POSITION = 60.6;
		// public final static double UPPER_NODE_POSITION = 85;
		// public final static double CARRIAGE_UP = 32.0;

		public final static double FLOOR_POSITION = 0.2;
		public final static double SHELF_POSITION = 45; // 59
		public final static double MIDDLE_NODE_POSITION = 60;
		public final static double UPPER_NODE_POSITION = 85;
		public final static double CARRIAGE_UP = 32.0;

		public final static float TOP_LIMIT = 90.0f;
		public final static float BOTTOM_LIMIT = 0.3f;
		public final static float RANGE = TOP_LIMIT - BOTTOM_LIMIT;
	}

	public final static class Arm {
		public final static int DRIVE_MASTER = 27; // left from ground side
		public final static int DRIVE_FOLLOWER = 28;

		public final static double KP = 0.7; // 0.00575
		public final static double KI = 0.0;
		public final static double KD = 0;
		public final static Constraints KCONSTRAINTS = new Constraints(9, 4);
		public final static double TOLERANCE = 0.1;

		public final static double KS = 0.14373;
		public final static double KV = 0.24083;
		public final static double KA = 5.2067;

		public final static float BOTTOM_LIMIT = 66500f;
		public final static float TOP_LIMIT = -9000f;

		//public final static double FLOOR_POSITION = 55000;
		public final static double SCORING_POSITION = 2; // 5.9 // -6000
		public final static double REST_POSITION = -3; //2.21
		public final static double CLAMP_POSITION = REST_POSITION; // 7.7

		// TODO: elevator 20 when we can drop arm
	}

	public final static class Claw {
		public final static int DRIVE = 35; // either 34 or 35
		// public final static int DRIVE_FOLLOWER = 34;

		public final static int IR_SENSOR = 0;

		public final static int PISTON = 7;

		public final static double CONE_SPEED = 0.5;
		public final static double CUBE_SPEED = 0.15;
	}

	public final static class Wrist {
		// TODO 3157
		public final static int DRIVE = 36;
		public final static int ENCODER = 9;

		public final static double KP = 0.18; // 0.1
		public final static double KI = 0;
		public final static double KD = 0.008;

		public final static double DOWN_POSITION = 31;
		public final static double RIGHT_POSITION = 65;
		public final static double UP_POSITION = DOWN_POSITION;
		public final static double LEFT_POSITION = 0;
	}

	public final static class Floortake {
		public final static int DRIVE = 51;
		public final static int FLIP = 50;

		public final static double PASSIVE_SPEED = -0.1;
		public final static double INTAKE_SPEED = -0.3; // -0.257
		public final static double SPIT_SPEED = 1;

		public final static float FLIP_IN_POS = -1.0f; // changed from 0.0 bc slop
		public final static float FLIP_OUT_POS = 20.0f;

		// TODO: run ff
		public final static double FLIP_KS = -0.092557;
		public final static double FLIP_KV = 12.617;
		public final static double FLIP_KA = 7.094;

		// TODO: tune pid
		public final static double FLIP_KP = 0.3;
		public final static double FLIP_KI = 0;
		public final static double FLIP_KD = 0;
	}

	public final static class Vision {
		public static enum Pipeline {
			DEFAULT(0), APRIL_TAG(1), TAPE(9);

			int id;

			Pipeline(int id) {
				this.id = id;
			}

			public int getId() {
				return id;
			}

			/**
			 * Gets id of pipeline for a specific April Tag ID, id 1 at pipeline 3, etc.
			 */
			public int getId(int ATID) {
				if (this.id == 1) {
					return ATID;
				}
				return id;
			}

			public static Pipeline getPipeline(int id) {
				for (Pipeline p : values()) {
					if (p.getId() == id) {
						return p;
					}
				}
				return DEFAULT;
			}
		}
	}

	public final static class RobotSpecs {
		// The left-to-right distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(23.75);
		// The front-to-back distance between the drivetrain wheels measured from center
		// to center.
		public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(23.75);

		public static final double MAX_VOLTAGE = 12.0;

		public static final double MAX_VELOCITY_METERS_PER_SECOND = (6380.0 / 60.0
				* SdsModuleConfigurations.MK4I_L2.getDriveReduction()
				* SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI) * 0.99; // 100% ~4.97 m/s
		public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND
				/ Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);

		public static final double MAX_VELOCITY_AUTO = 4.3;
		public static final double MAX_ACCELERATION_AUTO = 4;
	}

}
