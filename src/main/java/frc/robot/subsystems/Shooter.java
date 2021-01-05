/*----------------------------------------------------------------------------*/
/* Copyleft (c) 2017-2020 FIRST. All lefts Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import static com.revrobotics.ControlType.*;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * Testing Ziegler-Nichols PID tuning method https://youtu.be/UOuRx9Ujsog?t=648
 * 
 * 1. Set kI and kD to 0
 * 2. Increase kP until output is a "sustained and stable oscillation"
 * 3. Record the Critical Gain kC (the kP that caused the oscillation), and the oscillation period pC
 * 4. Set gains:
 * 	kP = 0.6*kC
 * 	kI = 2*kP/pC
 * 	kD = 0.125*kP*Pc
 */
public class Shooter extends TimedRobot

{
	CANSparkMax leftShooterMotor = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, kBrushless);
	CANEncoder leftEncoder = leftShooterMotor.getEncoder();
	CANPIDController shooterPID = leftShooterMotor.getPIDController();
    
    double kP, kI, kD;
    final double RPM_10FTLINE = 1350;

	public Shooter()
	{
		leftShooterMotor.restoreFactoryDefaults();		
        leftShooterMotor.setInverted(true);
        leftShooterMotor.setSmartCurrentLimit(40);
        
        shooterPID.setP(0.001);
        shooterPID.setI(0);
        // shooterPID.seti
        shooterPID.setD(0.01);
        leftShooterMotor.burnFlash();
    }
    
	public void robotPeriodic()
	{
		SmartDashboard.putNumber("Current Velocity", leftEncoder.getVelocity());
	}	
    
    public void shoot()
    {
        shooterPID.setReference(RPM_10FTLINE+200, kVelocity); // steady state err is 200, but I terms make it VIOLENT
    }

    public void stopShooter()
    {
        shooterPID.setReference(0, kVelocity);
    }
}
