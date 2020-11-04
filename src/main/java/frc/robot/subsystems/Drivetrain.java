package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Drivetrain {

    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, MotorType.kBrushless); 
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, MotorType.kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, MotorType.kBrushless);
    
    private static SpeedControllerGroup leftSide = new SpeedControllerGroup(lf, lb);
    private static SpeedControllerGroup rightSide = new SpeedControllerGroup(rf, rb);
  
    DifferentialDrive dt = new DifferentialDrive(leftSide, rightSide);

    XboxController driverController;

    /**
     * Constructs a drivetrain
     * @param driverController the driver controller
     */
    public Drivetrain(XboxController driverController)
    {
        this.driverController = driverController; 
    }
    
    /**
     * drives 
     */
    public void drive()
    {
        dt.arcadeDrive(.5*driverController.getY(Hand.kLeft), -.5*driverController.getX(Hand.kRight));
    }
} 