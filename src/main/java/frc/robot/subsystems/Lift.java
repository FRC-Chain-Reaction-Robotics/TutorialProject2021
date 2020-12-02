package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//imports here
import frc.robot.Constants;

public class Lift 
{
    CANSparkMax rightLiftMotor;
    CANSparkMax leftLiftMotor;    

    public Lift()
    {
        rightLiftMotor = new CANSparkMax(Constants.LIFT_MOTOR_ID, MotorType.kBrushless);
        leftLiftMotor = new CANSparkMax(Constants.LIFT_MOTOR2_ID, MotorType.kBrushless);
        rightLiftMotor.follow(leftLiftMotor, true); //Will invert Right Motor
    }
    
    
    /**
     * lift method
     */
    public void lift()
    {
        leftLiftMotor.set(0.5);
    }

    /**
     * stop method
     */
    public void stop()
    {
        leftLiftMotor.set(0);
    }
    
     /**
      * reset method
      */
    public void reset()
    {
        leftLiftMotor.set(-0.5); 
    }
    
}