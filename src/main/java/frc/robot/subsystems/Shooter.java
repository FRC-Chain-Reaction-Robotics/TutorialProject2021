package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import frc.robot.Constants;


public class Shooter
{
    CANSparkMax ShooterLiftMotor = new CANSparkMax(Constants.SHOOTER_LIFT_MOTOR_ID, kBrushless);
    CANSparkMax LEFTShooterMotor = new CANSparkMax(Constants.LEFT_SHOOTER_MOTOR_ID, kBrushless); 
    CANSparkMax RIGHTShooterMotor = new CANSparkMax(Constants.RIGHT_SHOOTER_MOTOR_ID, kBrushless);
    Limelight limelight;
    double Angle;
    
    /** DISCUSSION TIME
     * okay so last time we were trying to get the limelight to work so we could make the shooter lift to the right angle automatically
     *  
     * Code someone else used to follow a target. Tx needs replacing with Ty, don't know if this works for our robot
     * 
     * does anyone know how to use the limelight properly? cuz like im not sure how we can 
     * us
     * what does this do exactly? but by changing it to Ty doesnt work? im confused and idk what to do
     * 0, aimPID.calculate(limelight.getTx(), 0)); 
     * From the current code. It uses Tx to aim the drivetrain, so if we change it to Ty and use the shooterlift motor that should work, right?
     * It activates the drivetrain to track a target horizontaly
     * anyone know how to impement steering_adjust?
     */
            //guys i dont get how limelight works as a whole, ik that we can get the veritcal angle from like.getTy but like what do we need it to be to shoot or like uih98g98h idk
     
            //man I felt that
            
   //yea i mean to aim. is this the only way? 
   //I think an encoder is involved somewhere but idk where
   //how do we look at the old code ok
   //i think we should ask for a hint or something
   //I went to github and downloaded a copy of the 2021-xperi branch
    //then open with vscode
//We have everything set up to shoot on its own, but it doesn't aim.
//Rn we have two buttons. One lift up the shooter, the other shoots the ball, but neither actually aim. THe goal was to use a limelight so we could press one button
//have it aim, then shoot without having to manually set it up.
//do we need to use limelight to shoot the thing? cuz idk how to use it..yeah me neither...can someone please help me understand this, yeah I could use the info too... wait, is limelight the light on the drive.
// on the xperi file theres a method for aiming using hte limelight but i hthink we have to create a pid controller?
/** 
 * Oh we're just turning this whole subsytem green from how much we're talking
 * Convert it all
 * PIDController aimPID = new PIDController(0., 0, 0); yea lol
 * aimPID.calculate(limelight.getTx(), 0)); 
 * this was from the drivetrain
 * WAIT  i thinkt hese pid thinks jsut give us speed or something wiat because the parameters for the .arcadeDrive() method are speed and rotation but use limelight methods to ge tthose? idk
 * public void aim() {
        dt.arcadeDrive(
                // distPID.calculate(limelight.getTy(), 0),
                0, aimPID.calculate(limelight.getTx(), 0));
    }
 * 
 * I'm just confused and don't know how to use what's being put in
 * 
 * i guess a final solution would be to just set the angle to something and when someone like josh or jacob comes they can correct the issue we have regarding not being able to 
 * determine an angle to set the thing to shoot using limelight so like
 * go likt if (ty==1028309)
 * I guess we could ignore a limelight and use a PID to set an angle... we r already using limelight, so why not cont. using it...yeah I thought the same... fair enough... would it be easier to set the angle without a PID? I don't think so
 * 
 * guys i have an idea of how we can aim but i think it might involve an encoder or something
 * we can set the shoot method to shoot at any angle but we just have to determine how far the robot has to be away for the ball to reach the goal at that angle
 * would this work? to shoot the ball we would set the shooting mo
 * It's more comedic this way//I agree lol
 * 
 * 
 */

    public Shooter(Limelight ll) 
    {
        limelight = ll;
        RIGHTShooterMotor.follow(LEFTShooterMotor, true);
    }
    /**
     * Lifts the shooter
     */
    public void liftShooter()
    { 
        ShooterLiftMotor.set(0.5);
    }
    
    /** 
      Starts shooter Motor
    */
    public void shoot()
    {
       /**
       limelight.getTy()
       
        
       
        */ 
        double angle = limelight.getTy(); //the method is private to limelight, we probably need to create limelight object.  
        ShooterLiftMotor.getEncoder().get //??
        //would we do like if(ty =/= 0)  shooterLift
        //with doubles it isnt too precide to like determine 0. like it might be 0.00000001
        
        LEFTShooterMotor.set(0.5);
    }

    /**  Stops the shooter
    *
    */
    public void stop()
    {
        LEFTShooterMotor.set(0);
    }
    
    /**  lowers the shooter
    *
	*/
	public void lowerShooter()
    {
        ShooterLiftMotor.set(-0.5);        
    }
        
        
    public double getVelocity()
    {
        return LEFTShooterMotor.getEncoder().getVelocity();
    }
    /**
     * Activates drive train to track the target horizontally
     */
    public void horizontalTracker()
    {
        aimPID.calculate(limelight.getTx(), 0)); 
        
    }

    
  
}