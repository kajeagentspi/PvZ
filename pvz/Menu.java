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
	private JPanel menu;
	private GamePanel game;
	private MyFrame frame;
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
		// Thread thread=new Thread(this.game.getStage());
		// thread.start();
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
			}
			ois.close();
		}catch(Exception e){}

		try{
			FileInputStream fis = new FileInputStream("particle.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<ParticleVar> particlevarlist=new ArrayList<ParticleVar>();
			ArrayList<?> objectList = (ArrayList<?>) ois.readObject();
			if (objectList.size()>0){
				for(int i=0;i<objectList.size();i+=1){
					Object o=objectList.get(i);
					if(o instanceof ParticleVar){
						particlevarlist.add((ParticleVar)o);
					}
				}
			}
			for(int i=0;i<particlevarlist.size();i+=1){
				if(particlevarlist.get(i).getType().equals("Pea")){
					System.out.println("lol");
					game.getStage().addParticle(new Pea(particlevarlist.get(i),game.getStage()));
				}else if(particlevarlist.get(i).getType().equals("FrozenPea")){
					game.getStage().addParticle(new FrozenPea(particlevarlist.get(i),game.getStage()));
				}
			}
			ois.close();
		}catch(Exception e){
			System.out.println(e);
		}

		try{
			FileInputStream fis = new FileInputStream("zombie.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<ZombieVar> zombievarlist=new ArrayList<ZombieVar>();
			ArrayList<?> objectList = (ArrayList<?>) ois.readObject();
			if (objectList.size()>0){
				for(int i=0;i<objectList.size();i+=1){
					Object o=objectList.get(i);
					if(o instanceof ZombieVar){
						zombievarlist.add((ZombieVar)o);
					}
				}
			}
			for(int i=0;i<zombievarlist.size();i+=1){
				if(zombievarlist.get(i).getType().equals("Zombie")){
					game.getStage().addZombie(new Zombie(zombievarlist.get(i),game.getStage()));
				}
			}
			ois.close();
		}catch(Exception e){}

		game.getStage().resume();
		// Thread thread=new Thread(this.game.getStage());
		// thread.start();
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

		ArrayList<ParticleVar> particlevarlist=new ArrayList<ParticleVar>();
		for(int i=0;i<this.game.getStage().getParticleList().size();i+=1){
			particlevarlist.add(this.game.getStage().getParticleList().get(i).getParticleVar());
		}
		try{
			FileOutputStream fos = new FileOutputStream("particle.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(particlevarlist);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		ArrayList<ZombieVar> zombievarlist=new ArrayList<ZombieVar>();
		for(int i=0;i<this.game.getStage().getZombieList().size();i+=1){
			zombievarlist.add(this.game.getStage().getZombieList().get(i).getZombieVar());
		}
		try{
			FileOutputStream fos = new FileOutputStream("zombie.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(zombievarlist);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void quitGame(){
		System.exit(0);
	}
	
}