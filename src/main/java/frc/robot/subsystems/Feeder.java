package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * A one motor subsystem with a fast mode and a slow mode.
 */
public class Feeder 
{
    TalonSRX feedMotor = new TalonSRX(Constants.FEEDER_MOTOR_ID);
    
    DigitalInput bottom = new DigitalInput(0); 
    DigitalInput middle = new DigitalInput(1);
    DigitalInput top = new DigitalInput(2);

    DigitalInput[] inputs = {bottom,middle,top};
    
    int numBalls = 0;

    /**
     * Constructs a new Feeder with one motor and a controller.
     * @param x the controller
     */
    public Feeder() 
    {
        feedMotor.setInverted(true);
    }

    /**
     * call me in robotperiodic pls
     */
    public void updateBallCount()
    {
        int count = 0;
        if(!bottom.get()) {
            count++;
        }
        if(!middle.get()) {
            count++;
        }
        if(!top.get()) {
            count++;
        }
        numBalls = count;
        
        SmartDashboard.putNumber("balls", numBalls);
    }

    /**
     * numballs determine highest triggered sensor?
     * Just for feeder
     */
    public void addBall()
    {
        switch(numBalls)
        {
        
            case 0:
                if(bottom.get())    //  if there's no ball at the bottom
                    feed();
                break;
            case 1:
                if(middle.get())
                    feed();
                break;
            case 2:
                if(top.get())
                    feed();
                break;
            default:
                break;
        }
    }
    

//stuff that works VVV
    /**
     * turns on the feeder
     */
    public void feed()
    {
        feed(0.2);
    }

    /**
     * turns on the feeder
     * @param power the specificed power
     */
    public void feed(double power)
    {
        feedMotor.set(ControlMode.PercentOutput, power);
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