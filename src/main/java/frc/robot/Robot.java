/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import static edu.wpi.first.wpilibj.GenericHID.Hand.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot
{
	XboxController operatorController = new XboxController(1);
	XboxController driverController = new XboxController(0);
	
	Intake intaker = new Intake();
	Limelight ll = new Limelight();
	Drivetrain dt = new Drivetrain(ll); 
	Lift lift = new Lift();
	
	@Override
	public void teleopPeriodic()
	{
		//Manages intake components
		if (operatorController.getAButton())
			intaker.IntakeStart();	
		else
			intaker.IntakeStop();

		//Slows down if holding trigger
		if(driverController.getBumper(kLeft))
			dt.driveSlow(driverController.getY(kLeft), -driverController.getX(kRight));
		else
			dt.drive(driverController.getX(kLeft), -driverController.getX(kRight));
			   
		if(operatorController.getPOV() == 0)
			lift.raiseLift();
		else if(operatorController.getPOV() == 180)
			lift.lowerLift();
		else
			lift.stopLift();
	}
}
