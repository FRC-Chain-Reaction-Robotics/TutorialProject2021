package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
//imports here
import frc.robot.Constants;

public class Lift 
{
    XboxController controller;
    PWMVictorSPX liftMotor;
    PWMVictorSPX liftMotor2;
    PWMVictorSPX liftMotor3;    

    public Lift(XboxController x)
    {
        
        controller = x;
        liftMotor = new PWMVictorSPX(Constants.LIFT_MOTOR_ID); 
        liftMotor2 = new PWMVictorSPX(Constants.LIFT_MOTOR2_ID);
        liftMotor3 = new PWMVictorSPX(Constants.LIFT_MOTOR3_ID);
    }

    // public void teleOp()
    // {
    //     if( controller.getXButton() ) 
    //     {
    //         liftMotor.set(1);
    //     }
    //     else if( controller.getYButton() ) 
    //     {
    //         liftMotor.set(-1);
    //     }
    //     else 
    //     {
    //         liftMotor.set(0);
    //     }
    // }

    /**
     * lift goes up(1 motor)
     */
    public void up()
    {
        liftMotor.set(1);
    }

    /**
     * lift goes down(1 motor)
     */
    public void down()
    {
        liftMotor.set(-1);
    }

    public void stopLift() 
    {
        liftMotor.set(0);
    } 

    /**
     * winch pulls(2 motors)
     */
    public void pull() {
        liftMotor2.set(1);
        liftMotor3.set(1);
    }

    /**
     * winch releases(2 motors)
     */
    public void release()
    {
        liftMotor2.set(-1);
        liftMotor3.set(-1);
    }

    
    public void stopWinch() {
        liftMotor2.set(0);
        liftMotor3.set(0);
    }
    
}