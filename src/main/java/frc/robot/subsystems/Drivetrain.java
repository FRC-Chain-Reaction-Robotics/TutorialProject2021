package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Drivetrain
{
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, MotorType.kBrushless); 
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, MotorType.kBrushless);
    
    private static SpeedControllerGroup leftSide = new SpeedControllerGroup(lf, lb);
    private static SpeedControllerGroup rightSide = new SpeedControllerGroup(rf, rb);
  
    DifferentialDrive dt = new DifferentialDrive(leftSide, rightSide);

    Limelight limelight;

    // PIDController aimPID = new PIDController(0.035, 0, 0);
    PIDController aimPID = new PIDController(0., 0, 0);
    PIDController distPID = new PIDController(0.05, 0, 0);

    /**
     * Constructs a drivetrain
     * @param limelight the limelight 
     */
    public Drivetrain(Limelight limelight)
    {
        this.limelight = limelight;
        // dt.setMaxOutput(0.4);
    } 
    
    /**
     * Drives 
     */
    public void drive(double xSpeed, double zRotation)
    {
        dt.arcadeDrive(xSpeed, zRotation);
    }
    /**
     * Aims the robot+
     */
    public void aim()
    {
        dt.arcadeDrive(
            // distPID.calculate(limelight.getTy(), 0),
            0, aimPID.calculate(limelight.getTx(), 0)
        );
    }
} 