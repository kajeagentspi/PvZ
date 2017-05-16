import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
		public static final int DIMENSION_X = 1000;
		public static final int DIMENSION_Y = 625;
	public MyFrame (){
		this.setPreferredSize(new Dimension(1000,625));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Container container= this.getContentPane();
		Stage stage = new Stage();
		PlantBoard pboard=new PlantBoard(stage);
		PausePanel pause =new PausePanel(stage);
		container.add(pboard,BorderLayout.NORTH);
		container.add(stage,BorderLayout.CENTER);
		stage.requestFocus();
		this.pack();
		this.setVisible(true);
		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
	    	public void keyPressed(KeyEvent e) {
	        	if (e.getKeyCode() == 27){   //this is the code for the escape key
	        		if(!stage.getStatus()){
						stage.setStatus();
						stage.pause();
						pause.setVisible(true);
					}
					else if(stage.getStatus()){
						stage.setStatus();
						stage.resume();
						pause.setVisible(false);
					}
	    		}
	    	}
			public void keyReleased(KeyEvent e) {}
		});
		Thread thread=new Thread(stage);
		thread.start();
	}
}