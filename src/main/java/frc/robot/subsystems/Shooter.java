package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;

public class Shooter
{
    XboxController controller;
    
    CANSparkMax leftShooterMotor = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    CANSparkMax rightShooterMotor = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    CANPIDController lpidController = leftShooterMotor.getPIDController();

    public Shooter(XboxController x)
    {
        controller = x;
        rightShooterMotor.setInverted(true);
    }

    // public void teleOp()
    // {
    //     if(controller.getXButton())
    //     {
    //         if (controller.getBumper(Hand.kRight))
    //             this.shoot(ShotRange.kInitLine);
    //         else
    //             this.shoot(ShotRange.kTrench);
    //     }
    // }

    /**
     * turn on
     */
    public void shoot()
    {
        leftShooterMotor.set(.5);
        rightShooterMotor.set(.5);
        lpidController.setReference(1600, ControlType.kVelocity);
    }

    /**
     * turn on
     * @param power specified power
     */
    public void shoot(int power){
        leftShooterMotor.set(power);
        rightShooterMotor.set(power);
    }

     /**
      * turn off
      */
    public void stopShooter() //sooter kekw
    {
        leftShooterMotor.set(0);
        rightShooterMotor.set(0);
    }
}