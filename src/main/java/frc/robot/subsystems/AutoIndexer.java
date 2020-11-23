package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoIndexer {
    
    public AutoIndexer() {
        DigitalInput bottom = new DigitalInput(0); 
        DigitalInput middle = new DigitalInput(1);
        DigitalInput top = new DigitalInput(2);
    }

    /*
    if(input2.get() && input1.get() && !input3.get()) {
        turn on motor to push power cells
    }
        
x`

    }
    */
    // public void AutoIndexerFull(){
    //     if(!input3.get() && !input1.get()){
    //         //turns on shooter until it's empty of powercells
    //         while(input1.get()) {
    //         //turn on shooter...
    //         } 
    //     }
    //     else{
    //         //keep intaking...
    //     }
    //     if(!input2)
    // }
    

/* 0-2 balls
    |		
    | top true
    |  
    |	
    |
    | middle true
    |       
    |	
    |
    | bottom true              ball(s) over here
    |___________________
*/



/* 3 balls
    |		
    | top   TRUE
    |  
    |	
    |
    | middle TRUE
    |       
    |	
    |
    | bottom FALSE
    |___________________
*/

/*   4 balls
    |		
    | top True
    |  
    |	
    |
    | middle False
        |       
    |	
    |
    | bottom False
    |___________________
*/

/*    5 balls  
    |		
    | top False
    |  
    |	
    |
    | middle False
    |       
    |	
    |
    | bottom False
    |___________________
*/
}