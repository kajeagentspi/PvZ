package pvz;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
class Menu extends JPanel{
	private JButton newgame;
	private JButton highscores;
	private JButton quit;
	private JPanel menu;
	private GamePanel game;
	private MyFrame frame;
	public Menu(MyFrame frame){
		this.frame=frame;
		this.newgame=new JButton("New Game");
		this.highscores=new JButton("High Scores");
		this.quit=new JButton("Quit");
		this.menu=new JPanel();
		this.menu.add(this.newgame);
		this.menu.add(this.highscores);
		this.menu.add(this.quit);

		frame.getContainer().add(this.menu,"menu");
		CardLayout c = (CardLayout) frame.getContainer().getLayout();
		c.show(frame.getContainer(),"menu"); 
		newgame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newGame();
				CardLayout c = (CardLayout) frame.getContainer().getLayout();
				c.show(frame.getContainer(),"game"); 
				game.requestFocus();
				}
			});
		highscores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadScores();
				CardLayout c = (CardLayout) frame.getContainer().getLayout();
				c.show(frame.getContainer(),"highscore");
				game.requestFocus();
			}
		});
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				quitGame();
			}
		});
	}
	public void showMenu(){
		CardLayout c = (CardLayout) frame.getContainer().getLayout();
		c.show(frame.getContainer(),"menu");
		this.menu.requestFocus();
	}
	public void newGame(){
		this.game=new GamePanel(this);
		this.game.requestFocus();
		frame.getContainer().add(this.game,"game");
	}
	public void loadScores(){
		
	}

	public void saveScores(){
		
	}
	public void quitGame(){
		System.exit(0);
	}
	
}