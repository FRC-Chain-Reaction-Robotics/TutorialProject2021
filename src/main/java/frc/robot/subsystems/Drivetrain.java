
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;



import frc.robot.Constants;

public class Drivetrain 
{
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, kBrushless);
    private static CANSparkMax lm = new CANSparkMax(Constants.LM_MOTOR_ID, kBrushless);
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, kBrushless);
    private static CANSparkMax rm = new CANSparkMax(Constants.RM_MOTOR_ID, kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, kBrushless);

    private static SpeedControllerGroup leftSide = new SpeedControllerGroup(lb, lm, lf);
    private static SpeedControllerGroup rightSide = new SpeedControllerGroup(rb, rm, rf);

    DifferentialDrive dt = new DifferentialDrive(leftSide, rightSide);
    Limelight limelight; 
    
    public Drivetrain(Limelight limelight) {
      this.limelight = limelight;
      dt.setMaxOutput(0.5);
    }
    
    //TODO: @joshua tankdrive or arcadedrive??
    public void drive(double xSpeed, double zRotation) {
      dt.arcadeDrive(xSpeed, zRotation);
    }

    public void driveSlow(double xSpeed, double zRotation){
      drive(0.5 * xSpeed, zRotation);
    }
}
