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
	Ramp r = new Ramp();
	Shooter shooter = new Shooter();
	
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
		
		if(driverController.getBumper(kRight))
			r.pushTime(0.5, 2.0);	
		
		if(operatorController.get/**button */)
			shooter.liftShooter();
		else
			shooter.lowerShooter();
			//cuz i feel lik one button should both lift n shoot u know? //ok lets go back to Shooter.java or not idk 
			//le ts do ii m yea, we need some method to like determine when to lift then shoot, all in one button though
			//this is fancy timer stuff right.imma rewatch the vidwait
			//same ok
			//what is the difference between lift motor and shoot motor:: lift brings it at the angle
			//ok how about we find when the lift stops lifting, and gets to the desired angle, then shoot
			//we could do psuedo code for the time and bs it
			//so doesnt limelight determine the angle to lift to 
			
			/** PSEUDO CODE:
			we need to figure out how to use limelight with the lift
			angle = getTy() <-- this is the angle the lift motor will lift to
			does the angle it needs to lift to not vary
			it does, that's why we need the limelight
			oh
			
			

yea MOVE TO SHOOOTER CLASS EVERYONE

			
			 */  
		if(operatorController.getBButton())
			shooter.shoot();
			
		else
			shooter.stop(); 

	}
}
