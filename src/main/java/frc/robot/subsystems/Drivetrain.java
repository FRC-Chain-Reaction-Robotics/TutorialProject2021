package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static com.revrobotics.ControlType.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
//import edu.wpi.first.wpilib.kinematics.*; 
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import frc.robot.Constants;

public class Drivetrain {
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, MotorType.kBrushless);

    private static SpeedControllerGroup leftSide = new SpeedControllerGroup(lf, lb);
    private static SpeedControllerGroup rightSide = new SpeedControllerGroup(rf, rb);

    DifferentialDrive dt = new DifferentialDrive(leftSide, rightSide);

    Limelight limelight;

    PIDController aimPID = new PIDController(0.1, 0, 0);
    PIDController distPID = new PIDController(0.1, 0, 0);

    // --- Below this is for trajectories and stuff lol
    Gyro gyro = new ADXRS450_Gyro();

    CANEncoder m_leftEncoder = lf.getEncoder();
    CANEncoder m_rightEncoder = rf.getEncoder();

    // https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html#tuning-methods
    // oooh!
    // TODO: Use the SPARK MAX Client application to set PID coeffs that will
    // persist!
    CANPIDController leftPID = lf.getPIDController();
    CANPIDController rightPID = rf.getPIDController();

    // Odometry class for tracking robot pose
    DifferentialDriveOdometry odometry;
    
    Trajectory trajectory;
    RamseteController controller = new RamseteController();
    
    // this 0.5 in the next line is the drivetrain wheelbase width
    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.5); // ***needs a look at...


    /**
     * Constructs a drivetrain
     * 
     * @param limelight the limelight
     */
    public Drivetrain(Limelight limelight) {
        this.limelight = limelight;
        dt.setMaxOutput(0.5);

        m_leftEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
        m_rightEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);

        m_leftEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
        m_rightEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);

        // m_rightEncoder.setInverted(true);

        gyro.reset();
        odometry = new DifferentialDriveOdometry(new Rotation2d(Math.toRadians(gyro.getAngle())));

        /*
		* --- WARNING!!! ---
		* The following code means that you HAVE to set up the DS in the SAME spot the robot is placed, 
		* because the DS position determines the chosen trajectory.
		* Seems normal but just specifying... When testing, just choose it in the DS app.
        */
        //TODO:  switch to smartdashboard selector
		String path = Integer.toString(DriverStation.getInstance().getLocation());

		// OPENING TRAJECTORY
		try {
			Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve("PathWeaver/output/" + path);
			trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open trajectory: " + path, ex.getStackTrace());
		}
    }

    /**
     * Drives
     */
    public void drive(double xSpeed, double zRotation) {
        dt.arcadeDrive(xSpeed, zRotation);
    }

    /**
     * Aims the robot+
     */
    public void aim() {
        dt.arcadeDrive(
                // distPID.calculate(limelight.getTy(), 0),
                0, aimPID.calculate(limelight.getTx(), 0));
    }
     
    public void followTrajectory(double time)
    {
        //  represents next position of the robot
        Trajectory.State goal = trajectory.sample(time);
        //  gives the speed for left and right sides
		ChassisSpeeds adjustedSpeeds = controller.calculate(this.getPose(), goal);  //  should be the *current* robot pose
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(adjustedSpeeds);
        
        double leftSpeed = wheelSpeeds.leftMetersPerSecond;
        double rightSpeed = wheelSpeeds.rightMetersPerSecond;

		leftPID.setReference(leftSpeed, kVelocity);
        rightPID.setReference(rightSpeed, kVelocity);
    }

    /**
	 * call me during periodic pls ;)
	 */
    public void updateOdometry()
    {
        // TODO: might be radians, not degrees?
        //  TODO: make sure getPosition is same as getDistance
        odometry.update(
                new Rotation2d(Math.toDegrees(gyro.getAngle())),
                m_leftEncoder.getPosition(),
                m_rightEncoder.getPosition()
            );
    }
    /**
     * Returns the current position of the robot in meters
     * @return the position of the robot
     */
    public Pose2d getPose()
    {
        return odometry.getPoseMeters();
    }
}