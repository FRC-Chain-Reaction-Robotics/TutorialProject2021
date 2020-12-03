
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Drivetrain 
{
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, kBrushless); 
    private static CANSparkMax lm = new CANSparkMax(Constants.LM_MOTOR_ID, kBrushless); // I think we dont need the bottom ones... the upper ones are instance variables... IDK what the bottom ones are lol
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, kBrushless);
    private static CANSparkMax rm = new CANSparkMax(Constants.RM_MOTOR_ID, kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, kBrushless); 

   /** DifferentialDrive m_drive;
    CANSparkMax m_frontLeft = new CANSparkMax(LF_MOTOR_ID, kBrushless); 
    CANSparkMax m_midLeft = new CANSparkMax(LM_MOTOR_ID, kBrushless);
    CANSparkMax m_rearLeft = new CANSparkMax(LB_MOTOR_ID, kBrushless);
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

    CANSparkMax m_frontRight = new CANSparkMax(RF_MOTOR_ID, kBrushless);
    CANSparkMax m_midRight = new CANSparkMax(RM_MOTOR_ID, kBrushless);
    CANSparkMax m_rearRight = new CANSparkMax(RB_MOTOR_ID, kBrushless);
    SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

*/}


/**
 * Straight from the Wpilib
 * 
 * Talon m_frontLeft = new Talon(1);
   Talon m_midLeft = new Talon(2);
   Talon m_rearLeft = new Talon(3);
   SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

   Talon m_frontRight = new Talon(4);
   Talon m_midRight = new Talon(5);
   Talon m_rearRight = new Talon(6);
   SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

   DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
 */