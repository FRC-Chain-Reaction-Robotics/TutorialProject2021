package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Intake {
    CANSparkMax intakeMotor;
    CANSparkMax leftHopperMotor;
    TalonSRX rightHopperMotor;
    
    /** 
     * Constructs the intake motor 
     */
    public Intake() {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushed);
        leftHopperMotor = new CANSparkMax(Constants.LEFT_HOPPER_MOTOR_ID, MotorType.kBrushed);
        rightHopperMotor = new TalonSRX(Constants.RIGHT_HOPPER_MOTOR_ID);

    }

    // public void teleOp()    
    // {
    //     //#region Intake
    //     if(controller.getXButton())
    //         intakeMotor.set(1);
    //     else if(controller.getYButton())
    //         intakeMotor.set (-1);
    //     else
    //         intakeMotor.set (0);
    //     //#endregion Intake

    //     //#region Hopper
        
    //     //#endregion Hopper

        
    // }
    
    /**
     * Turns on the intake motor
         */
    public void intake()
    {
        intake(1);
        //second method beautifully crafted by the one and only gian, absoulutely not basic java. advanced stuff.
    }
        
    /**
    * Turns on the intake motor
    * @param power the specificed power
    */
    public void intake(double power)
    {
        intakeMotor.set(-power*.5);
        leftHopperMotor.set(-power*.5);
        rightHopperMotor.set(ControlMode.PercentOutput, -power*.5);
    }

    /**
    * turns off the intake
    */   
    public void stopMotor()
    {
        intake(0);
    }
}

