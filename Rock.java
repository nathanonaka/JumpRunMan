
public class Rock {
	//instantiate variables and make it public
	public int x, y;
	public EZImage image;
	public String ImageName;
	//make Sticker object
	public Rock(EZImage fimage, String fImageName, int fx, int fy) {
		x =fx;
		y = fy;
		image = fimage;
		ImageName = fImageName;
	}
	//method to return x coordinate
	public int getx(){
		return x;
	}
	//method to return y coordinate
	public int gety(){
		return y;
	}
	//method to return Imagename
	public String getimagename(){
		return ImageName;
	}
	//method to return image
	public EZImage getimage(){
		return image;
	}
	public boolean isOnRock(int posx, int posy) {
		return image.isPointInElement(posx, posy);
	}
}
