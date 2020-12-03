import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;
import frc.robot.Constants;


public class Intake
{
	//TODO: use talonsrx (@josh add vendordep talonsrx)
	CANSparkMax intakeMotor;
	
	public Intake()
    {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, kBrushless);
    }
}