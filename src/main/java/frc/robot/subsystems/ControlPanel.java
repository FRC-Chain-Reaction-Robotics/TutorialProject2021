package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;

/**
 * A one motor system that can move in either direction.
 */
public class ControlPanel {
    
    
    private CANSparkMax panelMotor;

    private ColorSensorV3 colorSensor;
    private final ColorMatch colorMatcher = new ColorMatch();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    
    private final ArrayList<Color> colors = new ArrayList<>();
    
    public Color gameColor = null;

    private Timer timer = new Timer();
    private boolean hasAlreadyStarted;
    
    /**
     * Constructs a ControlPanel object.
     */
    public ControlPanel()
    {
        panelMotor = new CANSparkMax(Constants.CONTROL_MOTOR_ID, MotorType.kBrushless); 
        colorSensor = new ColorSensorV3(Constants.COLOR_SENSOR_ID);
      
        
        colorMatcher.addColorMatch(kBlueTarget);
        colorMatcher.addColorMatch(kGreenTarget);
        colorMatcher.addColorMatch(kRedTarget);
        colorMatcher.addColorMatch(kYellowTarget);
        colors.add(kRedTarget);
        colors.add(kGreenTarget);
        colors.add(kBlueTarget);
        colors.add(kYellowTarget);
    
    }
    
 

    /**
     * wheel go spin (but only 3-5 times
     */
    public void rotationControl()
    {
        double startTime = 0.0;
        if(!hasAlreadyStarted) {
            timer.start();
            panelMotor.set(0.5);
            startTime = timer.get();
            hasAlreadyStarted = true;
        }
        
        //"timeInterval" would be some amount we experiment for
        if(timer.get() - startTime > 20.0 /*Needs to be tested for later*/ ) {
            timer.stop();
            panelMotor.set(0);
            hasAlreadyStarted = false;
        }
    }
    
    /**
     * wheel spins, but not that much
     */
    public void positionControl()
    {
        //this is the color the robot sees - which is offset from what the field sees
        Color color = colors.get((colors.indexOf(gameColor) + 2)  % colors.size()); //the modulo is so that if it's above the length, it loops back to the beginning of the array
        
        if(colorMatcher.matchClosestColor(colorSensor.getColor()).color != color)
            panelMotor.set(1);
        else
            panelMotor.set(0);
    }

    /**
     * Gets the color of this match
     */
    public void getGameColor()
    {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
            switch (gameData.charAt(0) )
            {
                case 'B' :
                    gameColor = kBlueTarget;
                    break;
                case 'G' :
                    gameColor = kGreenTarget;
                    break;
                case 'R' :
                    gameColor = kRedTarget;
                    break;
                case 'Y' :
                    gameColor = kYellowTarget;
                    break;
                default :
                    //This is corrupt data - Tell Shuffleboard there was an error and no color is known
                    break;
            }
        }
    }
}