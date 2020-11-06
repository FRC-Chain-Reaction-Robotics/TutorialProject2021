package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

/**
 * A one motor subsystem with a fast mode and a slow mode.
 */
public class Feeder 
{
    TalonSRX feedMotor;

    /**
     * Constructs a new Feeder with one motor and a controller.
     * @param x the controller
     */
    public Feeder() 
    {
        feedMotor = new TalonSRX(Constants.FEEDER_MOTOR_ID);
        feedMotor.setInverted(true);
    }

    /**
     * turns on the feeder
     */
    public void feed()
    {
        feedMotor.set(ControlMode.PercentOutput, 1);
    }

    /**
     * turns on the feeder
     * @param power the specificed power
     */
    public void feed(double power)
    {
        feedMotor.set(ControlMode.PercentOutput, 1);
        //gian wrote this beautifully crafted method
        // 😎✔✔ :^)
    }
    
     /**
      * turns off the feeder
      */
    public void stopMotor() 
    {
        feedMotor.set(ControlMode.PercentOutput, 0);
    }

}