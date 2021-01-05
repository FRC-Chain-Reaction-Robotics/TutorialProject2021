package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Intake
{
    CANSparkMax intakeMotor;
    CANSparkMax leftHopperMotor;
    TalonSRX rightHopperMotor;
    
    public Intake()
    {
        // TODO: Electrical: Use TalonSRXs for all of these, and make them followers as necessary
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushed);
        leftHopperMotor = new CANSparkMax(Constants.LEFT_HOPPER_MOTOR_ID, MotorType.kBrushed);
        rightHopperMotor = new TalonSRX(Constants.RIGHT_HOPPER_MOTOR_ID);
        intakeMotor.setInverted(false);
    }

    /**
     * Turns on the intake motor
     */
    public void intake()
    {
        intake(0.5);
    }

    /**
    * turns off the intake
    */   
    public void stop()
    {
        intake(0);
    }

    /**
    * Turns on the intake motor
    * @param power the specificed power
    */
    public void intake(double power)
    {
        intakeMotor.set(power);
        leftHopperMotor.set(power);
        rightHopperMotor.set(ControlMode.PercentOutput, power);
    }
}

