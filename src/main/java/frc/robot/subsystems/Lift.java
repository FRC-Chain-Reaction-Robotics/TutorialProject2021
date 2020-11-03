package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//imports here
import frc.robot.Constants;

public class Lift 
{
    XboxController controller;
    CANSparkMax liftMotor;
    CANSparkMax liftMotor2;
    CANSparkMax liftMotor3;    

    public Lift(XboxController x)
    {
        
        controller = x;
        liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_ID, MotorType.kBrushless);
        liftMotor2 = new CANSparkMax(Constants.LIFT_MOTOR2_ID, MotorType.kBrushless);
        liftMotor3 = new CANSparkMax(Constants.LIFT_MOTOR3_ID, MotorType.kBrushless);
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