import java.awt.Color;

public class Brick {
	private int x, y, height, width;
	private Color c;
	 
	public Brick() {
		x = 0; y = 0;
		height = 50; width = 50;
	}
	
	public Brick(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
		this.c = c;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height; 
	}
	
	public Color getColor() {
		return c;
	}
}
