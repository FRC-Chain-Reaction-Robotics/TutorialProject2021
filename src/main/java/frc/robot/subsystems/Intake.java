package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Intake {
    CANSparkMax intakeMotor;
    
    /** 
     * Constructs the intake motor 
     */
    public Intake() {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
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
        intakeMotor.set(1);
        //second method beautifully crafted by the one and only gian, absoulutely not basic java. advanced stuff.
    }
        
    /**
    * Turns on the intake motor
    * @param power the specificed power
    */
    public void intake(double power)
    {
        intakeMotor.set(power);
    }

    /**
    * turns off the intake
    */   
    public void stopMotor()
    {
        intakeMotor.set(0);
    }
}

