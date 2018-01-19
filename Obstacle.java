public class Obstacle {

	private EZImage obstaclePicture;
	private double x, y;
	private double speed = 3;
	private int maxScreenX, maxScreenY;

	// Constructor for obstacle.
	public Obstacle(String filename, int maxX, int maxY) {
		obstaclePicture = EZ.addImage(filename, maxX, maxY);
		maxScreenX = maxY;
		maxScreenY = maxY;
		init();
	}

	// Initialize the obstacle by randomizing its stating position on the screen.
	public void init() {
		setPosition(500, 1300);
		speed = .5;
	}

	// Accesser function to get the X and Y position of the obstacle.
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	// Set the position of the obstacle.
	public void setPosition(double posx, double posy) {
		x = posx;
		y = posy;
		setObstacleImagePosition(x, y);
	}

	// Set the image position of the obstacle.
	private void setObstacleImagePosition(double posx, double posy) {
		obstaclePicture.translateTo(posx, posy);
	}

	// Move the obstacle.
	public void move() {
		y = y - speed;
		setPosition(x, y);
	}

	// Check to see if a point is inside an obstacle.
	public boolean isInsideLava(int posx, int posy) {
		return obstaclePicture.isPointInElement(posx, posy);
	}
}
