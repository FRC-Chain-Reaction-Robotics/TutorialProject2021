package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import com.revrobotics.*;
import frc.robot.*;



public class Ramp {
    TalonSRX rampMotor;
    private Timer timer = new Timer();
    public Ramp() {
        rampMotor = new TalonSRX(Constants.RAMP_MOTOR_ID);
    }
    public void push(double power)
    {
        rampMotor.set(ControlMode.PercentOutput, power);
    }

    //pushes for specified amt of time
    public void pushTime(double power, double time)
    {
		if(rampMotor.getPower() == 0)
		{
            timer.start();
            push(0.5);
        }
        if(timer.get() >= time) /* num of seconds it would take to push it down */)
        {
            timer.stop();
            rampMotor.set(0);
            
        }
    }
}

/// TODO: import talonsrx packages and show them how