import java.awt.Color;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
public class Project3 {
	
	/*Team Member roles
	 * Damien: Programmed the Man class and the ControlIt method in order to move the character
	 * Zach: Programmed the move methods, and the gravity function
	 * John:Programmed the Obstacle class and set the position of the lava
	 * Nathan:Programmed Game class and each of the different state if statements
	 * 
	 * Version 2 tasks, must be able to move character in basic movement, left right up
	 * gravity and lava will be added in version 3
	 * all other code is extra for the next milestone
	 */
	
	static final int MAX_SCREEN_X = EZ.getWindowWidth();
	static final int MAX_SCREEN_Y = EZ.getWindowHeight();
	static int stam;
	//variables to determine the state of the character
	static final int MAN_RUNNING = 0;
	static final int MAN_JUMPING = 1;
	static final int MAN_WINS = 2;
	static final int MAN_IS_KILL = 3;
	static final int MAN_WINS_ALL = 4;
	static final int MAN_FALLING = 5;

	public static void main(String[] args) throws java.io.IOException {
		int manState = MAN_RUNNING;
		int lvl = 0;
		//time before falling
		int timer = 5;
		// Setup EZ graphics system.
		EZ.initialize(800, 800);
		// Set Background Color to brown
		EZ.setBackgroundColor(new Color(139, 69, 19));
		
		ArrayList<Rock> allRocks = new ArrayList<Rock>();

		// adds the background image and the lava image
		EZImage background = EZ.addImage("cave.png", MAX_SCREEN_X, 525);			
		Man maMan = new Man("man.png", 500, 400);
		Obstacle lava = new Obstacle("lava.png", 500, 1300);
		EZImage tempRock = EZ.addImage("lrock.png", 500, 500);
		EZImage tempRock2 = EZ.addImage("mrock.png", 400, 400);
		
		allRocks.add(new Rock(tempRock, "lrock.png", 500, 500));
		allRocks.add(new Rock(tempRock2, "mrock.png", 400, 400));
		
		//sets the characters state to running
		while (manState == MAN_RUNNING)
		{
			maMan.ControlRun();
			//move the lava
			if (EZInteraction.isKeyDown('w'))
			{
			manState = MAN_JUMPING;
			}
			lava.move();
			
			//checks to see if the character is touching the lava, and sets state to killed
			if ((lava.isInsideLava(maMan.getX() - 30,
					maMan.getY() - 30))
					|| (lava.isInsideLava(maMan.getX() + 30,
							maMan.getY() - 30))
					|| (lava.isInsideLava(maMan.getX() - 30,
							maMan.getY() + 30))
					|| (lava.isInsideLava(maMan.getX() + 30,
							maMan.getY() + 30))) {
				manState = MAN_IS_KILL;
			}

			//if the character is at the top of the screen set state to win
			if (maMan.getY() < 50)
			{
				manState = MAN_WINS;
			}
			
			// Make sure to do this or else the graphics wonʻt refresh
			EZ.refreshScreen();	

		}
		
		while (manState == MAN_JUMPING)
		{
			stam = 100;
			while (stam > -1 && manState == MAN_JUMPING)
			{
			lava.move();
			maMan.ControlJump();
			stam --;
			maMan.moveUp(1);
			EZ.refreshScreen();
			if (stam == 0 || EZInteraction.wasKeyReleased('w')){
				manState = MAN_FALLING;
			}
			}
		}
		
		while(manState == MAN_FALLING)
		{
			maMan.ControlFall();
			
			lava.move();
			
			if ((lava.isInsideLava(maMan.getX() - 30,
					maMan.getY() - 30))
					|| (lava.isInsideLava(maMan.getX() + 30,
							maMan.getY() - 30))
					|| (lava.isInsideLava(maMan.getX() - 30,
							maMan.getY() + 30))
					|| (lava.isInsideLava(maMan.getX() + 30,
							maMan.getY() + 30))) {
				manState = MAN_IS_KILL;
			}
			for(int i = 0; i < allRocks.size(); i++)
			{
			if(allRocks.get(i).isOnRock(maMan.getX() - 40, maMan.getY() - 40) || allRocks.get(i).isOnRock(maMan.getX() - 40, maMan.getY() +40)
					|| allRocks.get(i).isOnRock(maMan.getX() + 40, maMan.getY() - 40) || allRocks.get(i).isOnRock(maMan.getX() + 40, maMan.getY() + 40))
			{
				System.out.println("hit rock");
				manState = MAN_RUNNING;
				while (manState == MAN_RUNNING)
				{
					maMan.ControlRun();
					//move the lava
					if (EZInteraction.isKeyDown('w'))
					{
					manState = MAN_JUMPING;
					}
					lava.move();
					
					//checks to see if the character is touching the lava, and sets state to killed
					if ((lava.isInsideLava(maMan.getX() - 30,
							maMan.getY() - 30))
							|| (lava.isInsideLava(maMan.getX() + 30,
									maMan.getY() - 30))
							|| (lava.isInsideLava(maMan.getX() - 30,
									maMan.getY() + 30))
							|| (lava.isInsideLava(maMan.getX() + 30,
									maMan.getY() + 30))) {
						manState = MAN_IS_KILL;
					}

					//if the character is at the top of the screen set state to win
					if (maMan.getY() < 50)
					{
						manState = MAN_WINS;
					}
					
					// Make sure to do this or else the graphics wonʻt refresh
					EZ.refreshScreen();	

				}
				while (manState == MAN_JUMPING)
				{
					stam = 100;
					while (stam > -1 && manState == MAN_JUMPING)
					{
					lava.move();
					maMan.ControlJump();
					stam --;
					maMan.moveUp(1);
					EZ.refreshScreen();
					if (stam == 0 || EZInteraction.wasKeyReleased('w')){
						manState = MAN_FALLING;
					}
					}
				}

			}
			}
			maMan.moveDown(1);
			EZ.refreshScreen();
			
		}
		
		if (manState == MAN_IS_KILL) 
        {
			
			// Show the message: You are kill
			Color c = new Color(0, 10, 150);
			int fontsize = 50;
			EZText text = EZ.addText(512, 300, "You are kill", c, fontsize);
		}

		// Show the message: SAFE...for now
		if (manState == MAN_WINS)
		{
			Color c = new Color(0, 255, 100);
			int fontsize = 50;
			EZText text = EZ.addText(300, 300, "SAFE...for now", c, fontsize);	
		}
		
		if(manState == MAN_WINS_ALL)
		{
			//Show message: SAFE FOREVER
			Color c = new Color(0, 255, 100);
			int fontsize = 50;
			EZText text = EZ.addText(300, 300, "SAFE FOREVER", c, fontsize);
		}
	
	EZ.refreshScreen();
	}
}
