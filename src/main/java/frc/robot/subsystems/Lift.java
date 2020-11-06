package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//imports here
import frc.robot.Constants;

public class Lift 
{
    CANSparkMax liftMotor;
    CANSparkMax lWinchMotor;
    CANSparkMax rWinchMotor;    

    public Lift()
    {
        liftMotor = new CANSparkMax(Constants.LIFT_MOTOR_ID, MotorType.kBrushless);
        lWinchMotor = new CANSparkMax(Constants.LIFT_MOTOR2_ID, MotorType.kBrushless);
        rWinchMotor = new CANSparkMax(Constants.LIFT_MOTOR3_ID, MotorType.kBrushless);
    }
    
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
        lWinchMotor.set(1);
        rWinchMotor.set(1);
    }

    /**
     * winch releases(2 motors)
     */
    public void release()
    {
        lWinchMotor.set(-1);
        rWinchMotor.set(-1);
    }

    
    public void stopWinch() {
        lWinchMotor.set(0);
        rWinchMotor.set(0);
    }
    
}