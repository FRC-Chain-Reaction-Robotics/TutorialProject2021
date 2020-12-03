package frc.robot.subsystems;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;
import frc.robot.Constants;

public class Lift
{
	CANSparkMax motorLeft = new CANSparkMax(Constants.LEFT_LIFT_MOTOR_ID, kBrushless);
	CANSparkMax motorRight = new CANSparkMax(Constants.RIGHT_LIFT_MOTOR_ID,kBrushless);
	
	public void raiseLift()
	{
		motorLeft.set(0.5);
		motorRight.set(0.5);
	}
	public void lowerLift()
	{
		motorLeft.set(-0.5);
		motorRight.set(-0.5);
	}
	public void stopLift()
	{
		motorLeft.set(0);
		motorRight.set(0);
	}
}