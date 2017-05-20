package pvz;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
class Menu extends JPanel{
	private JButton newgame;
	private JButton loadgame;
	private JButton quit;
	private MyFrame frame;
	private GamePanel game;
	private JPanel menu;
	public Menu(MyFrame frame){
		this.frame=frame;

		this.newgame=new JButton("New Game");
		this.loadgame=new JButton("Load Game");
		this.quit=new JButton("Quit");
		this.menu=new JPanel();
		this.menu.add(this.newgame);
		this.menu.add(this.loadgame);
		this.menu.add(this.quit);
		frame.getContainer().add(this.menu,"menu");
		CardLayout c = (CardLayout) frame.getContainer().getLayout();
		c.show(frame.getContainer(),"menu"); 
		newgame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newGame();
				CardLayout c = (CardLayout) frame.getContainer().getLayout();
				c.show(frame.getContainer(),"second"); 
				game.requestFocus();
				}
			});
		loadgame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadGame();
				CardLayout c = (CardLayout) frame.getContainer().getLayout();
				c.show(frame.getContainer(),"second");
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
	}
	public void newGame(){
		this.game=new GamePanel(this);
		this.game.requestFocus();
		frame.getContainer().add(this.game,"second");
		Thread thread=new Thread(this.game.getStage());
		thread.start();
	}
	public void loadGame(){
		//load
		this.game=new GamePanel(this);
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("save.pvz"));
			game=(GamePanel)in.readObject();
			in.close();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error loading");
		}
		frame.getContainer().add(this.game,"second");
		Thread thread=new Thread(this.game.getStage());
		thread.start();

		//insert animation shits
		game.getStage().resetnotQuit();
		game.getStage().resume();
	}
	public void quitGame(){
		System.exit(0);
	}
	public void saveGame(){
		try{
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("save.pvz"));
			out.writeObject(this.game);
			out.close();
		}catch(Exception e){}
	}
	

}