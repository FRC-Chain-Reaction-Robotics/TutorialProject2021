package frc.robot.subsystems;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.*;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class CRRPixyWrapper
{
	static Pixy2 pixy;
	private static final int x_center = 316/2;
	private static final int y_center = 208/2;

	public CRRPixyWrapper() {
		pixy = Pixy2.createInstance(new SPILink()); // Creates a new Pixy2 camera using SPILink
		pixy.init(); // Initializes the camera and prepares to send/receive data
		pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		pixy.setLED(255, 255, 255); // Sets the RGB LED to full white
	}

	/**
	 * right is positive error (i.e., pass this into the freaking PID lol)
	 */
	public int getXError()
	{
		return x_center - getBiggestBlock().getX();
	}

	/**
	 * down is positive i think?
	 */
	public int getYError()
	{
		return y_center - getBiggestBlock().getX();
	}

	private static Block getBiggestBlock() {
		// Gets the number of "blocks", identified targets, that match signature 1 on
		// the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 10, for a slight increase in
		// efficiency
		int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 10);
		System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		if (blockCount <= 0) {
			return null; // If blocks were not found, stop processing
		}
		ArrayList<Block> blocks = pixy.getCCC().getBlockCache(); // Gets a list of all blocks found by the Pixy2
		Block largestBlock = null;
		for (Block block : blocks) { // Loops through all blocks and finds the widest one
			if (largestBlock == null) {
				largestBlock = block;
			} else if (block.getWidth() > largestBlock.getWidth()) {
				largestBlock = block;
			}
		}
		return largestBlock;
	}
}