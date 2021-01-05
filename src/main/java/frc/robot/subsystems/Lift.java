package frc.robot.subsystems;

import static com.ctre.phoenix.motorcontrol.ControlMode.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

public class Lift 
{
    TalonSRX rightLiftMotor;
    TalonSRX leftLiftMotor;    

    public Lift()
    {
        rightLiftMotor = new TalonSRX(Constants.LIFT_LEFT_MOTOR_ID);
        leftLiftMotor = new TalonSRX(Constants.LIFT_RIGHT_MOTOR_ID);
        rightLiftMotor.follow(leftLiftMotor);
        rightLiftMotor.setInverted(true);
    }
    
    
    /**
     * lift method
     */
    public void lift()
    {
        leftLiftMotor.set(PercentOutput, 0.5);
    }

    /**
     * stop method
     */
    public void stop()
    {
        leftLiftMotor.set(PercentOutput, 0);
    }
    
     /**
      * reset method
      */
    public void reset()
    {
        leftLiftMotor.set(PercentOutput, -0.5); 
    }
    
}