package frc.robot.subsystems;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import java.io.IOException;
import java.nio.file.Path;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants;

public class Mecanum
{
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, kBrushless);
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, kBrushless);

    MecanumDrive md = new MecanumDrive(lf, lb, rf, rb);

    CANEncoder m_leftFrontEncoder = lf.getEncoder();
    CANEncoder m_leftBackEncoder = lb.getEncoder();
    CANEncoder m_rightFrontEncoder = rf.getEncoder();
    CANEncoder m_rightBackEncoder = rb.getEncoder();

	CANPIDController lf_PID = lf.getPIDController();
    CANPIDController lb_PID = lb.getPIDController();
    CANPIDController rf_PID = rf.getPIDController();
	CANPIDController rb_PID = rb.getPIDController();
    
    Translation2d m_frontLeftLocation = new Translation2d(0.3048, 0.2286);
    Translation2d m_frontRightLocation = new Translation2d(0.3048, -0.2286);
    Translation2d m_backLeftLocation = new Translation2d(-0.3048, -0.3048);
    Translation2d m_backRightLocation = new Translation2d(-0.3048, -0.3048);
    
    Gyro m_gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);
    MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(m_kinematics, new Rotation2d(-m_gyro.getAngle()), new Pose2d(5.0, 13.5, new Rotation2d()));    
    
    Trajectory trajectory;
    // TODO: Make a RamseteController here 
    // (Note: In the 2021 WPILib update, HolonomicDriveController will be available in the full release)

    PIDController aimPID = new PIDController(0.01, 0, 0);

    Limelight ll;
    CRRPixyWrapper pixy = new CRRPixyWrapper();
    
    public Mecanum(Limelight l) 
    {
        ll = l;
        md.setMaxOutput(.5);
        m_gyro.reset();
        m_gyro.calibrate();
        m_rightFrontEncoder.setPositionConversionFactor(.1524*Math.PI);
        m_rightBackEncoder.setPositionConversionFactor(.1524);
        m_leftFrontEncoder.setPositionConversionFactor(.1524);
        m_leftBackEncoder.setPositionConversionFactor(.1524); // .1524 is 6 inches in meters


    
        double choice = SmartDashboard.getNumber("Auton choice", 1);
        String trajectoryJSON = "paths/" + choice + ".wpilib.json";

        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
        }
    }
    
    /**
     * drives EX. "vroom vroom"
     * all directions thank you mecanum :D :)  B) 
     * :) .
     */
    public void drive(double xSpeed, double ySpeed, double zRotation)
    {
        // TODO: add slowmode toggle to both drivetrains
        md.driveCartesian(xSpeed, ySpeed, zRotation);
    }
    
    public void aimLL()
    {
        md.driveCartesian(0, 0, aimPID.calculate(ll.getTx(), 0));
    }

    public void aimPixy()
    {
        md.driveCartesian(0, 0, aimPID.calculate(pixy.getXError()));
    }
    
    public void followTrajectory(double time)
    {
        // TODO: implement
    }


    public void updateOdometry()
    {
        m_odometry.update(new Rotation2d(-m_gyro.getAngle()), new MecanumDriveWheelSpeeds(
                m_leftFrontEncoder.getVelocity(),
                m_leftBackEncoder.getVelocity(),
                m_rightFrontEncoder.getVelocity(),
                m_rightBackEncoder.getVelocity()));

        SmartDashboard.putNumberArray("fl fr bl br", new double[]{m_leftFrontEncoder.getPosition(), 
            m_rightFrontEncoder.getPosition(), 
            m_leftBackEncoder.getPosition(), 
            m_rightBackEncoder.getPosition()
        });
    }

    public Pose2d getLocation()
    {
        return m_odometry.getPoseMeters();
    }

    public void resetPose()
    {
        m_gyro.reset();

    }

    // displays the heading data in the gyro, form of a compass
	public void displayGyro()
	{
        Shuffleboard.getTab("Location: ").add((Sendable) m_gyro);
    }


    /* #region innacc */
    public void goToPoint()
    {
        double[] nums = {5,10,15}; //placeholder values for desired ty values

        md.driveCartesian(aimPID.calculate(ll.getTy(), findNearestValue(ll.getTy(),nums)), 0, aimPID.calculate(ll.getTx(), 0)); 
    }

    public double findNearestValue(double value, double[]nums)
    {
        int index = 0;
        for(int i = 1; i < nums.length; i++)
        {
            if(Math.abs(value-nums[index]) < Math.abs(value-nums[index])) index = i;
        }
        return nums[index];
    }
    /* #endregion */


    //you can probably just go ahead and put them in here, we can move later if we need to
    //store list in this method or put it somewhere else cause idk what to do with variables sweet
    //we already have an aim method in here, so tx is taken care of
}//so what do we do with the ty points 
//we need to figure out a way for the code to see the current ty, then move the bot to make the ty fit with one of the values we chose
/**
 * so we need to call getTy
 * 
 * 
 * find closest value out of list of values?
 * anyone know how to make it pick from a list like this?
 * kinda
 * 
 * drivecartesion until Ty == targetTy
 * 
 * then KABOOM
 * 
 */  
