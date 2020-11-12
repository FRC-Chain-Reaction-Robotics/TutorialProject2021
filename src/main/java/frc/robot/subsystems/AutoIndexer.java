package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoIndexer {
    
    public AutoIndexer() {
        DigitalInput input1 = new DigitalInput(0); //1-3 is lowest to highest in the feeder
        DigitalInput input2 = new DigitalInput(1);
        DigitalInput input3 = new DigitalInput(2);
    }

    /*
    if(input2.get() && input1.get() && !input3.get()) {
        turn on motor to push power cells
    }
    


    }
    */
    /*
    public void AutoIndexerFull(){
        if(!input3.get() && !input1.get()){
            //turns on shooter until it's empty of powercells
            while(input1.get()) {
            //turn on shooter...
            }
        }
        else{
            //keep intaking...
        }
        if(!input2)
    }
    */
    

/*
    	|		
        | input3
		|  
		|	
        |
        | input2
        |       
		|	
		|
        | input1
		|___________________
*/
}