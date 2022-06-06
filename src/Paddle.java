import java.util.ArrayList;

public class Paddle {
	private int x, y, x1, y1;


	public Paddle(int x, int y){
		this.x = x;
		this.y = y;
		x1 = 90;
		y1 = 3;
	}


	public int getX(){ //corner top left 
		return x;
	}

	public int getY(){ // corner top left start 
		return y;
	}

	public void setX(int x){ //corner top left 
		this.x = x;
	}


	public int getWidth(){ 
		return x1;
	}

	public int getHeight(){ 
		return y1;
	}
	
	public void setWidth(int x) {
		this.x1 = x;
	}
	
	
}
