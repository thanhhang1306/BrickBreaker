// Hang Pham - P3
import java.awt.*;
import java.util.ArrayList;


public class Ball {

	private String ball;
	private int x, y, s,  dx, dy;
	private boolean change, change1;
	private final int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 700;
	private boolean lose = false;
	private ArrayList<Brick> toRemove = new ArrayList<>();
	private int brickDestroyed;
//  private Player play;

	public Ball() {
		this.x = 0; 
		this.y = 0;
		this.s = 0;
		change = false; 
		change1 = false; 
		dx = 3; 
		dy = 3;
		brickDestroyed = 0; 
	}
	

	

	public String toString() {
		String str = "x" + x + "y:" + y + "s" + s + "dx" + dx + "dy" + dy;
		return str;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getSize(){ 
		return s;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSize(int s) {
		this.s = s;
	}
	

	public void setdx(int x){
		dx = x; 
	}

	public void setdy(int y){
		dy = y; 
	}

	
	public int getdx() {
		return dx;
	}
	public int getdy() {
		return dy;
	}
	
	public void reverse(){
		change = !change;
	}

	public void reverse1(){
		change1 = !change1;
	}
	
	public void setChange1(boolean b) {
		change1 = b;
	}

	public void move(){
		
		if(this.x < 0 || this.x > SCREEN_WIDTH - s - 10) 
			reverse();
		if(change)
			x+=dx;
		else x-=dx;
		
		if(this.y < 0)
			reverse1();
		if(change1)
			y+= dy;
		else y-=dy;
	}

	public void collidePaddle(Paddle p) {
		//if(getY() + getSize() >= p.getY() && getY() + getSize() <= p.getY() + p.getHeight()) {
		if(getY() + getSize() >= p.getY() && getY() + getSize() <= p.getY() + p.getHeight()) {
			if(getX() + getSize() >= p.getX() && getX() <= p.getX() + p.getWidth()) 
				reverse1();
		}
	}

	public boolean checkLoss() {
		if(getY() > SCREEN_HEIGHT)
			return true;
		else return false; 
	}
	
	public void setLose(boolean b) {
		lose = b;
	}
	
	public void collideBrick(ArrayList<Brick> b) {
		for(Brick brick:b) {
			if(getY() < brick.getY() + brick.getHeight() && getY() > brick.getY()) {
				if(getX() + getSize() >= brick.getX() && getX() <= brick.getX() + brick.getWidth()) {
					toRemove.add(brick);
					brickDestroyed++; 
					reverse1();
				}
			}	
		} 
		b.removeAll(toRemove);
	}

	public int getBrickDestroyed() {
		return brickDestroyed; 
	}
	
	public void setBrickDestroyed(int b) {
		brickDestroyed = b;
	}
}
