package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Shooter
{
    
    CANSparkMax leftShooterMotor = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    CANSparkMax rightShooterMotor = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, MotorType.kBrushless);
    CANPIDController lpidController = leftShooterMotor.getPIDController();

    public Shooter()
    {
        rightShooterMotor.setInverted(true);
        rightShooterMotor.follow(leftShooterMotor);
    }

    /**
     * turn on
     */
    public void shoot()
    {
        lpidController.setReference(1375, ControlType.kVelocity);
    }

    /**
      * turn off
      */
    public void stopShooter() //sooter kekw
    {
        lpidController.setReference(0, ControlType.kVelocity);
    }

	public double getVelocity() {
		return leftShooterMotor.getEncoder().getVelocity();
	}

	public double getSetpoint() {
		return leftShooterMotor.get();
	}
}