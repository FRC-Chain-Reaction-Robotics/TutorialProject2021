package frc.robot.subsystems;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Sendable;
import frc.robot.Constants;

public class Mecanum
{
    MecanumDrive md;

    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, kBrushless);
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, kBrushless); 

    CANEncoder m_leftFrontEncoder = lf.getEncoder();
    CANEncoder m_leftBackEncoder = lb.getEncoder();
    CANEncoder m_rightFrontEncoder = rf.getEncoder();
    CANEncoder m_rightBackEncoder = rb.getEncoder();

	CANPIDController lf_PID = lf.getPIDController();
    CANPIDController lb_PID = lb.getPIDController();
    CANPIDController rf_PID = rf.getPIDController();
	CANPIDController rb_PID = rb.getPIDController();
    
    Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
    Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
    Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
    Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);
    
    Gyro m_gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);
    MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(m_kinematics, new Rotation2d(-m_gyro.getAngle()), new Pose2d(5.0, 13.5, new Rotation2d()));    
    
    public Mecanum() 
    {
        md = new MecanumDrive(lf, lb, rf, rb);
        md.setMaxOutput(0.35);
    }
    
    /**
     * drives EX. "vroom vroom"
     * all directions thank you mecanum :D :)  B) 
     * :) .
     */
    public void drive(double xSpeed, double ySpeed, double zRotation)
    {
        //what are xspeed, yspeed, and zrotation gonna be
        md.driveCartesian(xSpeed, ySpeed, zRotation);
    }
    
    public void updateOdometry()
    {
        m_odometry.update(new Rotation2d(-m_gyro.getAngle()), new MecanumDriveWheelSpeeds(
                m_leftFrontEncoder.getVelocity(),
                m_leftBackEncoder.getVelocity(),
                m_rightFrontEncoder.getVelocity(),
                m_rightBackEncoder.getVelocity()));
    }

    public Pose2d getLocation()
    {
        return m_odometry.getPoseMeters();
    }

    // displays the heading data in the gyro, form of a compass
	public void displayGyro()
	{
        Shuffleboard.getTab("Location: ").add((Sendable) m_gyro);
    }
}