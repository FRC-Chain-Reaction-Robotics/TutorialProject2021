package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

/**
 * A one motor subsystem with a fast mode and a slow mode.
 */
public class Feeder 
{
    CANSparkMax feedMotor;

    /**
     * Constructs a new Feeder with one motor and a controller.
     * @param x the controller
     */
    public Feeder() 
    {
        feedMotor = new CANSparkMax(Constants.FEEDER_MOTOR_ID, MotorType.kBrushless);
    }

    /**
     * Goes fast, slow, or not at all depending on the controller input
     */
    // public void teleOp() 
    // {
    //     if (controller.getBumperReleased(Hand.kRight))
    //     {
    //         feedMotor.set(1);
    //     } else if(controller.getBumperReleased(Hand.kLeft))
    //     {
    //         feedMotor.set(0.3);
    //     } else
    //     {
    //         feedMotor.set(0);
    //     }
    // }

    /**
     * turns on the feeder
     */
    public void feed()
    {
        feedMotor.set(1);
    }

    /**
     * turns on the feeder
     * @param power the specificed power
     */
    public void feed(double power)
    {
        feedMotor.set(power);
        //gian wrote this beautifully crafted method
        // 😎✔✔ :^
    }
    
     /**
      * turns off the feeder
      */
    public void stopMotor() 
    {
        feedMotor.set(0);
    }

}