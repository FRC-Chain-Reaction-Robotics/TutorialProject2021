package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Intake {
    XboxController controller;
    PWMVictorSPX intakeMotor;
    
    /** 
     * Constructs the intake motor along with the Xbox Controller.
     */
    public Intake(XboxController x) {
        controller = x;
        intakeMotor = new PWMVictorSPX(Constants.INTAKE_MOTOR_ID);
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
     * turn on intake
         */
    public void intake()
    {
        intakeMotor.set(1);
        //second method beautifully crafted by the one and only gian, absoulutely not basic java. advanced stuff.
    }
        
    /**
    * turns on the intake
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

