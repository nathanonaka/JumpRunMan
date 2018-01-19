public class Man {
	private EZImage manpicture;
	private int x, y;
	private int a, b;
	private int stam;
	
	// Constructor for creating a man.
	public Man(String filename, int posx, int posy) {
		x = posx;
		y = posy;
		manpicture = EZ.addImage("man.png", x, y);
	}

	// Accessor method to retrieve the position of the man.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Set the position of the man
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setManImagePosition(x, y);
	}

	private void setManImagePosition(int posx, int posy) {
		manpicture.translateTo(posx, posy);
	}

	// Methods for moving the man.
	public void moveLeft(int step) {
		x = x - step;
		setManImagePosition(x, y);
	}
	//methods to move the character left right and down 
	public void moveRight(int step) {
		x = x + step;
		setManImagePosition(x, y);
	}

	public void moveUp(int step) {
		y = y - step;
		setManImagePosition(x, y);
	}
	public void moveDown(int GRAVITY){
		y = y + GRAVITY;
		setManImagePosition(x, y);
	}


	// Keyboard controls for moving the man.
	public void ControlRun() {
		if (EZInteraction.isKeyDown('a')) {
			moveLeft(1);
		}
		if (EZInteraction.isKeyDown('d')) {
			moveRight(1);
		}
	}
	public void ControlFall() {
		if (EZInteraction.isKeyDown('a')){
			moveLeft(1);
		}
		if (EZInteraction.isKeyDown('d')){
			moveRight(1);
		}
	}
	public void ControlJump() {
		
			if (EZInteraction.isKeyDown('a')){
			moveLeft(1);
		}
			if (EZInteraction.isKeyDown('d')){
			moveRight(1);
		}

	}

}
