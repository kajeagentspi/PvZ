package pvz;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class Menu extends JPanel{
	private JButton newgame;
	private JButton highscores;
	private JButton quit;
	private JPanel menu;
	private GamePanel game;
	private MyFrame frame;
	private GameOver gameOver;
	private Image bg;
	private HashMap<String,Integer> scoreBoard;
	public Menu(MyFrame frame){
		this.frame=frame;
		this.newgame=new JButton();
		this.highscores=new JButton();
		this.quit=new JButton();
		this.menu=new JPanel();
		// Loading the buttons' images
		this.bg = this.loadImage("sprites/menus/backdrop.png");
		this.loadIcon(newgame, "sprites/menus/NEW GAME.png");
		this.loadIcon(highscores, "sprites/menus/HIGH SCORES.png");
		this.loadIcon(quit, "sprites/menus/QUI.png");
		this.scoreBoard=this.loadScores();
		//buttons
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
	public void showGameOver(){
		CardLayout c = (CardLayout) frame.getContainer().getLayout();
		this.gameOver=new GameOver(this.game);
		frame.getContainer().add(this.gameOver,"gameOver");
		c.show(frame.getContainer(),"gameOver");
		this.gameOver.requestFocus();
	}
	public void newGame(){
		this.game=new GamePanel(this);
		this.game.requestFocus();
		frame.getContainer().add(this.game,"game");
		this.gameOver=null;
	}
	public HashMap<String,Integer> loadScores(){
		try{
			FileInputStream fis = new FileInputStream("scoreBoard.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			HashMap<String,Integer>  readBack = (HashMap<String,Integer>)   ois.readObject();
			return readBack;
		}catch(Exception e){}
		return new HashMap<String,Integer>();
	}

	public void saveScores(String name,int score){
		this.scoreBoard.put(name,score);
		for(String key : this.scoreBoard.keySet()){
			System.out.println("key: " + key + " value: " + this.scoreBoard.get(key));
		}
		Map<String, Integer> ldapContent = (Map<String, Integer>)this.scoreBoard;
		Properties properties = new Properties();
		properties.putAll(ldapContent);
		try{
			properties.store(new FileOutputStream("data.properties"), null);
		}catch(Exception e){}
	}
	public void quitGame(){
		System.exit(0);
	}
	
	public void loadIcon(JButton button,String buttonlocation){
		try {
			Image img = Toolkit.getDefaultToolkit().getImage(new URL("file:"+buttonlocation));
			button.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
	}
	
	private Image loadImage(String imageLocation){
		try{
			return Toolkit.getDefaultToolkit().getImage(new URL("file:"+imageLocation));
		} catch(Exception e){}	
		return null;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 0, 0, null);//backgroundimage
		
	}
	
	
	
}