package frc.robot.subsystems;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;
import frc.robot.Constants;


public class Intake
{
	//TODO: use talonsrx (@josh add vendordep talonsrx)
	CANSparkMax intakeMotor;
    
    //constructor
	public Intake()
    {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, kBrushless);
    }
    
    public void IntakeStart()
    {
        intakeMotor.set(0.5);
    }
    
    public void IntakeStop()
    {
        intakeMotor.set(0);
    }
}