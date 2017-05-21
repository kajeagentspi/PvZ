package pvz;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

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
		this.game=new GamePanel(this);
		this.game.requestFocus();
		frame.getContainer().add(this.game,"second");
		try{
			FileInputStream fis = new FileInputStream("plant.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<PlantVar> plantvarList=new ArrayList<PlantVar>();
			ArrayList<?> objectList = (ArrayList<?>) ois.readObject();
			if (objectList.size()>0){
				for(int i=0;i<objectList.size();i+=1){
					Object o=objectList.get(i);
					if(o instanceof PlantVar){
						plantvarList.add((PlantVar)o);
					}
				}
			}
			for(int i=0;i<plantvarList.size();i+=1){
				if(plantvarList.get(i).getType().equals("PeaShooter")){
					game.getStage().addPlant(new PeaShooter(plantvarList.get(i),game.getStage()));
				}else if(plantvarList.get(i).getType().equals("CherryBomb")){
					game.getStage().addPlant(new CherryBomb(plantvarList.get(i),game.getStage()));
				}else if(plantvarList.get(i).getType().equals("WallNut")){
					game.getStage().addPlant(new WallNut(plantvarList.get(i),game.getStage()));
				}else if(plantvarList.get(i).getType().equals("SnowPea")){
					game.getStage().addPlant(new SnowPea(plantvarList.get(i),game.getStage()));
				}
				game.getStage().resume();

			}
			ois.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		Thread thread=new Thread(this.game.getStage());
		thread.start();

	}
	public void quitGame(){
		System.exit(0);
	}
	public void saveGame(){
		ArrayList<PlantVar> plantvarList=new ArrayList<PlantVar>();
		for(int i=0;i<this.game.getStage().getPlantList().size();i+=1){
			plantvarList.add(this.game.getStage().getPlantList().get(i).getPlantVar());
		}
		try{
			FileOutputStream fos = new FileOutputStream("plant.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(plantvarList);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}