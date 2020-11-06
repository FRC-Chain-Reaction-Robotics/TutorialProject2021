/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  XboxController driverController = new XboxController(0);
  XboxController operatorController = new XboxController(1);  
  
  Lift liftControl;
  Intake intakeControl = new Intake();
  Shooter shooterControl = new Shooter();
  Feeder feedControl = new Feeder();
  ControlPanel controlPanel = new ControlPanel();
  Limelight ll = new Limelight();
  Drivetrain dt = new Drivetrain(ll);
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotPeriodic()
  {
    // if(controlPanel.gameColor == null)
      // controlPanel.getGameColor();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    SmartDashboard.putNumber("limelight tx", ll.getTx());
    SmartDashboard.putNumber("limelight ty", ll.getTy());
    SmartDashboard.putBoolean("limelight tv", ll.getTv());
    SmartDashboard.putNumber("limelight ta", ll.getTa());
    SmartDashboard.putNumber("shooter rpm", shooterControl.getVelocity());
    SmartDashboard.putNumber("shooter setpoint", shooterControl.getSetpoint());

    System.out.println("tee ecks : " + ll.getTx());

    if (operatorController.getAButton())
      feedControl.feed();
    else 
      feedControl.stopMotor();

    if (operatorController.getXButton())
      intakeControl.intake();
    else
      intakeControl.stopMotor();
      
    if (operatorController.getBButton())
    {
        dt.aim();
        shooterControl.shoot();
    }
    else    
        dt.drive(.5*driverController.getY(Hand.kLeft), -.5*driverController.getX(Hand.kRight));
  
    
    // //#region ControlPanel
    // if(controller.getAButton() && !controller.getBButton())
    //     rotationControl();
    // else if(controller.getBButton() && !controller.getAButton())
    //     positionControl();
    // //#endregion
    

    // // TODO: actually call the lift methods
    // if(operatorController.getPOV() == 0) 
    //   liftControl.up();
    // else if(operatorController.getPOV() == 180) 
    //   liftControl.down();
    // else 
    //   liftControl. // there are so many methods wth? limit switch much??
  }  
}
