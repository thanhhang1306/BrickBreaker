import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*; 


public class Game  extends JPanel implements Runnable, KeyListener,  MouseListener, MouseMotionListener{


	private BufferedImage back; 
	private int key; 
	private ArrayList<Brick> brickList;
	private final int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 700;
	private int BRICK_WIDTH = 100, BRICK_HEIGHT = 30;
	private int PADDLE_WIDTH = 90;
	private String[] song;
	private Player play;
	private int BALL_SIZE = 20;
	private char screen;
	private Paddle paddle;
	private Ball ball; 
	private ImageIcon background, menu, heart, win, lose, levelP, rule;
	private boolean visibleBall, moveBall, levelStart;
	private int live, level;
	private int getPX;
	private boolean removeAll, musicWinLose;

	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		back = null;
		paddle = new Paddle((SCREEN_WIDTH-PADDLE_WIDTH)/2,600);
		ball = new Ball();
		play = new Player();
		song = new String[]{"Two.wav","Seven.wav","Six.wav","Ten.wav","One.wav","Five.wav","Three.wav","Eight.wav","Four.wav","Nine.wav"};
		background = new ImageIcon("background.png");
		menu = new ImageIcon("menu.png");
		heart = new ImageIcon("heartt.png");
		win = new ImageIcon("win.png");
		lose = new ImageIcon("lose.png");
		levelP = new ImageIcon("level.png");
		rule = new ImageIcon("rule.png");
		key =-1; 
		live = 3;
		getPX = (SCREEN_WIDTH-PADDLE_WIDTH)/2;
		screen = 'M';
		visibleBall = false; moveBall = false;
		levelStart = false; 
		removeAll = false;
		musicWinLose = false;
		play.playmusic(song[0]);
	}




	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e)
		{
		}
	}



	public ArrayList<Brick> getBrick() { 
		ArrayList<Brick> list = new ArrayList<>();
		switch(level) {
		case 1: 
			for (int w = 0; w<SCREEN_WIDTH/BRICK_WIDTH;w++) {
				for (int h = 0; h<(SCREEN_HEIGHT/3)/BRICK_HEIGHT; h++){
					list.add(new Brick(w*BRICK_WIDTH, h*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
				}
			}
			ball.setdx(3);
			ball.setdy(3);
			break;
		case 2: 
			for (int w = 0; w<(SCREEN_WIDTH/BRICK_WIDTH/3) + 1;w++) {
				for (int h = 0; h<(SCREEN_HEIGHT/3 + 50)/BRICK_HEIGHT; h++){
					list.add(new Brick(3*w*BRICK_WIDTH,  (h+1)*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
				}
			}
			ball.setdx(4);
			ball.setdy(4);
			break;
		case 3: 
			for (int i = 0; i < SCREEN_WIDTH/BRICK_WIDTH; i++) {
				list.add(new Brick(BRICK_WIDTH*i, BRICK_HEIGHT*i+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
			}
			for (int i = 0; i < SCREEN_WIDTH/BRICK_WIDTH; i++) {
				list.add(new Brick(BRICK_WIDTH*i, BRICK_HEIGHT*(9-i)+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
			}
			ball.setdx(4);
			ball.setdy(4);
			break;
		case 4: 
			for (int i = 1; i < 9; i++){
				if(i !=4 && i !=5) {
					list.add(new Brick(BRICK_WIDTH*i, BRICK_HEIGHT*i+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(BRICK_WIDTH*i, BRICK_HEIGHT*(9-i)+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(BRICK_WIDTH*(i+4), BRICK_HEIGHT*(9-i)+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(BRICK_WIDTH*(i+4), BRICK_HEIGHT*i+20, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));			
				}
			}
			list.add(new Brick((SCREEN_WIDTH-BRICK_WIDTH)/2, 150,  BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
			ball.setdx(5);
			ball.setdy(5);
			break;
		case 5:
			for (int w = 0; w<SCREEN_WIDTH/BRICK_WIDTH;w++) {
				for (int h = 0; h<2; h++){
					list.add(new Brick(w*BRICK_WIDTH, 9*h*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
				}
			}

			for (int i = 0; i < 2; i++) {
				for (int j = 1; j<(SCREEN_HEIGHT/2)/BRICK_HEIGHT - 2; j++){
					list.add(new Brick(i*9*BRICK_WIDTH, j*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
				}
			}
			ball.setdx(5);
			ball.setdy(5);
			break;
		case 6: 
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					list.add(new Brick(4*BRICK_WIDTH + BRICK_WIDTH*i, 3*BRICK_HEIGHT + 5*j*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(3*BRICK_WIDTH + BRICK_WIDTH*3*i, 2*BRICK_HEIGHT + 5*j*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(2*BRICK_WIDTH + BRICK_WIDTH*5*i, 3*BRICK_HEIGHT + 3*j*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
					list.add(new Brick(BRICK_WIDTH + BRICK_WIDTH*7*i, 4*BRICK_HEIGHT + j*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
				}
			}
			for (int i = 0; i<2; i++) {
				list.add(new Brick((SCREEN_WIDTH-BRICK_WIDTH)/2, 4*BRICK_HEIGHT + 5*i*BRICK_HEIGHT, BRICK_WIDTH - 5, BRICK_HEIGHT - 5, setRandomColor()));
			}
			ball.setdx(6);
			ball.setdy(6);
			break;
		default: 
			screen = 'N';
		}
		return list;
	}






	public void paint(Graphics g){

		Graphics2D twoDgraph = (Graphics2D) g; 
		if( back ==null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 


		Graphics g2d = back.createGraphics();

		g2d.clearRect(0,0,getSize().width, getSize().height);
		drawGame(g2d);
	
		twoDgraph.drawImage(back, null, 0, 0);

	}

	public void drawGame(Graphics g) {
		switch(screen) {
		case 'M':
			g.drawImage(menu.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			break;
		case 'N':
			g.drawImage(levelP.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			resetLevel();
			break;
		case 'R': 
			g.drawImage(rule.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			break;
		case 'G': 
			g.drawImage(background.getImage(), 0,0,SCREEN_WIDTH, SCREEN_HEIGHT, this);
			setBricks();
			paintBricks(g);		
			paintPaddle(g);
			paintBall(g);
			moveBall(g);
			resetScreen();
			checkResult();
			removeAll();
			break;		
		case 'W': 
			g.drawImage(win.getImage(), 0,0,SCREEN_WIDTH, SCREEN_HEIGHT, this);
			if(getMusicWinLose()) {
				stopMusic();
				play.playmusic(song[8]);
				musicWinLose = false;
			}
			break;
		case 'L': 
			g.drawImage(lose.getImage(), 0,0,SCREEN_WIDTH, SCREEN_HEIGHT, this);
			if(getMusicWinLose()) {
				stopMusic();
				play.playmusic(song[9]);
				musicWinLose = false;
			}
			break;
		}
	}

	public void setBricks() {
		if(levelStart) {
			brickList = getBrick();
			level = 0;
			levelStart = false;
		}
	}

	public void setXPaddle() {
		paddle.setX((SCREEN_WIDTH-PADDLE_WIDTH)/2);
	}
	
	public void paintBricks(Graphics g) {
		for (Brick brick: brickList) {
			g.setColor(brick.getColor());
			g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
		}
	}

	public int setRandomInt() {
		return (int)(Math.random()*106) + 150;
	}

	public Color setRandomColor() {
		Color random_Color = new Color(setRandomInt(), setRandomInt(), setRandomInt());
		return random_Color;
	}

	public void paintPaddle(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
	}

	public void paintBall(Graphics g) {
		g.setColor(setRandomColor());
		g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
	}

	public void reset() {
		visibleBall = false;
		moveBall = false;
		ball.setChange1(false);
		paddle.setX((SCREEN_WIDTH-PADDLE_WIDTH)/2);
		ball.setX(0);
		ball.setY(0);
		ball.setSize(1);
		ball.setBrickDestroyed(0);
		removeAll = false;
		paddle.setWidth(PADDLE_WIDTH);
	}

	public void resetScreen() {
		if(ball.checkLoss()) {
			reset();
			live--;
		}
	}

	public void resetLevel() {
		reset();
		setBricks();
		live = 3;
		level = 0;
	}

	public void checkResult() {
		if(live==0 && brickList.size() > 0) {
			musicWinLose = true;
			screen = 'L';
		}
		if(brickList.size() == 0) {
			musicWinLose = true;
			screen = 'W';
		}
	}
	
	public boolean getMusicWinLose() {
		return musicWinLose;
	}
	
	public void moveBall(Graphics g) {
		if(moveBall) {
			ball.move();
			ball.collidePaddle(paddle);
			ball.collideBrick(brickList);
		}
		else {
			g.setColor(Color.white);
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.setFont(new Font("Garamond", Font.ITALIC, 30));
			g.drawString("drag your mouse to move the paddle!", (SCREEN_WIDTH)/2 - metrics.stringWidth("drag your mouse to move the paddle!") - 20, 375);
			g.drawString("and remember, don't drop the ball in the void", (SCREEN_WIDTH)/2 - metrics.stringWidth("and remember, don't drop the ball in the void")/2, SCREEN_HEIGHT/2+75);
			g.drawString("live", 375, 505);
			switch (live) {
			case 3: 
				g.drawImage(heart.getImage(), 525, 475, 50, 50, this);
			case 2: 
				g.drawImage(heart.getImage(), 475, 475, 50, 50, this);
			case 1:
				g.drawImage(heart.getImage(), 425, 475, 50, 50, this);
			}
		}
	}

	public void removeAll() {
		if(removeAll) {
			if(brickList.size()>0)
				brickList.remove(0);

		}
	}

	

	public void stopMusic(){
		play.playmusic("stop");
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}





	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		key= e.getKeyCode();
		char charKey = (char)(key);
		if(screen == 'G') {
			if(key == 38) 
				removeAll = true;
			if(key == 40)
				brickList.remove(0);
			if(key == 37)
				paddle.setWidth(PADDLE_WIDTH-40);
			if(key == 39)
				paddle.setWidth(PADDLE_WIDTH+40);
			if(key == 32) 
				paddle.setWidth(PADDLE_WIDTH);
				
		}
		
	}



	@Override
	public void keyReleased(KeyEvent e) {



	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		getPX = arg0.getX();
		if (arg0.getX() >0 && arg0.getX() < SCREEN_WIDTH-PADDLE_WIDTH) {
			paddle.setX(getPX);
			if(!visibleBall && screen == 'G') {
				ball.setX((SCREEN_WIDTH-BALL_SIZE)/2);
				ball.setSize(BALL_SIZE);
				ball.setY(paddle.getY() - ball.getSize());		
				visibleBall = true;
				moveBall = true;
			}
		}

	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		switch(screen) {
		case 'M': 
			screen = 'N';
			stopMusic();
			play.playmusic(song[7]);
			break;
		case 'W': 
			screen = 'N';
			stopMusic();
			play.playmusic(song[7]);
			break;
		case 'L': 
			screen = 'N';
			stopMusic();
			play.playmusic(song[7]);
			break;
		case 'R': 
			screen = 'N';
			break;
		case 'N': 
			if(arg0.getY() > 564 && arg0.getY() < 664) {
				if(arg0.getX() > 190 && arg0.getX() < 463)
					screen = 'R';
				else if (arg0.getX()>534 && arg0.getX() < 821)
					System.exit(0);
			}
			for(int i = 1; i < 7; i++) {
				if(i < 4) {
					if(arg0.getY() > 71 && arg0.getY() < 258) {
						if(arg0.getX() > 303*(i-1) + 71 && arg0.getX() < 303*(i-1) + 71 + 262) {
							level = i;
							screen = 'G';
							levelStart = true;
							stopMusic();
							play.playmusic(song[i]);
							break;
							
						}
					}
				}
				else if(i > 3){
					if(arg0.getY() > 331 && arg0.getY() < 518) {
						if(arg0.getX() > 303*(i -4) + 71 && arg0.getX() < 303*(i -4) + 71 + 262) {
							level = i;
							screen = 'G';
							levelStart = true; 
							stopMusic();
							play.playmusic(song[i]);
							break;
						}
					}
				}
				else level = 0;
			}
			break;
			

		}


	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
