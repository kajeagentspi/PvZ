import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
		private GamePanel game;
		private Container container= this.getContentPane();
		public int mxPos;
		public int myPos;
	public MyFrame (){
		this.setPreferredSize(new Dimension(1000,625));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.newGame();
		this.pack();
		this.setVisible(true);
		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 27){   //this is the code for the escape key
					if(!game.getStage().getStatus()&&game!=null){
						game.getStage().setStatus();
						game.getStage().pause();
					}
					else if(game.getStage().getStatus()&&game!=null){
						game.getStage().setStatus();
						game.getStage().resume();
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
	//get coords
	this.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent me){
			mxPos= me.getX();
			myPos= me.getY();
			if(game!=null)
				game.getPboard().showCoords(mxPos,myPos);
			}
		});
	//temp code

	//FIX DOUBLE PLANTS
	this.addMouseListener(new MouseListener(){

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			int row=myPos/100;
			int col=mxPos/100;
			row-=1;
			if(        game.getStage().checkSpace(row,col))
				System.out.println("Row:"+(row)+" Col:"+col);
				System.out.println();
				game.getStage().addPlant(new PeaShooter(col*100,row*100,game.getStage()));
			}	
		});
	//temp code end
	}
	


	public void newGame(){
		game=new GamePanel();
		container.add(game,BorderLayout.CENTER);//change this shit to cardlayout
		Thread thread=new Thread(game.getStage());
		thread.start();
	}
	public void loadGame(){
		//load
		Thread thread=new Thread(game.getStage());
		thread.start();
	}
}