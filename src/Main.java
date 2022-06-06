import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
	private static final int WIDTH =1010;
	private static final int HEIGHT=700;
	
	public Main () {
		super("Brick Breaker!");
		setSize(WIDTH, HEIGHT);
		Game play = new Game();
		((Component) play).setFocusable(true);
		
		Color background = new Color(247, 232, 217);
		setBackground(background);
		
		
		getContentPane().add(play);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}
