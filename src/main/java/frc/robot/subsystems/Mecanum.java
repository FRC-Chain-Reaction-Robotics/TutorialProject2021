package frc.robot.subsystems;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;

public class Mecanum
{
	MecanumDrive md;
	
    private static CANSparkMax lf = new CANSparkMax(Constants.LF_MOTOR_ID, kBrushless);
    private static CANSparkMax lb = new CANSparkMax(Constants.LB_MOTOR_ID, kBrushless);
    private static CANSparkMax rf = new CANSparkMax(Constants.RF_MOTOR_ID, kBrushless);
    private static CANSparkMax rb = new CANSparkMax(Constants.RB_MOTOR_ID, kBrushless); 
	
    public Mecanum() 
    {
        md = new MecanumDrive(lf, lb, rf, rb);
        md.setMaxOutput(0.5);
    }

    /**
     * drives
     * all directions thank you mecanum :D :)
     * :) 
     */
    public void drive(double xSpeed, double ySpeed, double zRotation)
    {
        //what are xspeed, yspeed, and zrotation gonna be
        md.driveCartesian(xSpeed, ySpeed, zRotation);
    }
}