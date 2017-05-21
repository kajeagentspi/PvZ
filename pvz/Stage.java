package pvz;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.Serializable;

 // for sounds
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class Stage extends JPanel implements Runnable{
	private ArrayList<Particle> particleList;
	private ArrayList<Zombie> zombieList;
	private ArrayList<Plant> plantList;
	private ArrayList<Thread> particleThreads;
	private ArrayList<Thread> zombieThreads;
	private ArrayList<Thread> plantThreads;
	private boolean isPaused;
	private int timer;
	
	private transient URL url;
	private transient Clip clip;
	private transient AudioInputStream ais;
	private transient Thread threadTemp;
	private Image background;
	private long clipTime;
	private boolean notDead;
	private boolean notQuit;
	public Stage(){
		this.notQuit=true;
		this.notDead=true;
		this.setLayout(null);
		this.isPaused=false;
		particleList=new ArrayList<Particle>();
		zombieList=new ArrayList<Zombie>();
		plantList=new ArrayList<Plant>();
		particleThreads=new ArrayList<Thread>();
		zombieThreads=new ArrayList<Thread>();
		plantThreads=new ArrayList<Thread>();
		this.addZombie(new Zombie(1000,100,this));
		this.addZombie(new Zombie(1000,200,this));
		this.addZombie(new Zombie(1000,400,this));


		try{
			// for sound
			this.url = new URL("file:audio/pvzBG1.wav");
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);   
		}catch(Exception e){}
		// Thread threadZCreator=new Thread(new ZombieCreator(this));
		// threadZCreator.start();
		this.setPreferredSize(new Dimension(1000, 500));
		this.playBG(this.clip); // for soun

	}
	public Image getBackgroundImage(){
		try{
			return (Image)ImageIO.read(new URL("file:sprites/menus/backdrop.png"));//background image
		}catch(Exception e){}
		return null;
	}

	public void addPlant(Plant plant){
		this.plantList.add(plant);
		threadTemp=new Thread(plant);
		this.add(plant);
		threadTemp.start();
		this.plantThreads.add(threadTemp);
	}

	public void addParticle(Particle particle){
		this.particleList.add(particle);
		threadTemp=new Thread(particle);
		this.add(particle);
		threadTemp.start();
		this.particleThreads.add(threadTemp);
	}

	public void addZombie(Zombie zombie){
		this.zombieList.add(zombie);
		threadTemp=new Thread(zombie);
		this.add(zombie);
		threadTemp.start();
		this.zombieThreads.add(threadTemp);
	}

	public boolean zombieCheck(int xPos,int yPos){
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(zombieList.get(i).getRectangle().intersects(new Rectangle(xPos,yPos,(1000-xPos),100)))
					return true;
			}
		}return false;
	}


	public void clearDeadZombie(Zombie zombie){
		this.remove(zombie);
		this.zombieList.remove(zombie);
		
	}
	public void clearDeadParticle(Particle particle){
		this.remove(particle);
		this.particleList.remove(particle);
	}
	public void clearDeadPlants(Plant plant){
		this.remove(plant);
		this.plantList.remove(plant);	
	}
		
	public void pause(){
		for(int i=0;i<zombieList.size();i+=1){
			zombieList.get(i).pause();
		}for(int i=0;i<particleList.size();i+=1){
			particleList.get(i).pause();
		}for(int i=0;i<plantList.size();i+=1){
			plantList.get(i).pause();
		}this.clipTime= this.clip.getMicrosecondPosition();
		this.clip.stop();
	}

	public void resume(){
		for(int i=0;i<zombieList.size();i+=1){
			zombieList.get(i).resume();
		}for(int i=0;i<particleList.size();i+=1){
			particleList.get(i).resume();
		}for(int i=0;i<plantList.size();i+=1){
			plantList.get(i).resume();

		}this.clip.setMicrosecondPosition(this.clipTime);
		this.clip.start();
	}

	public boolean getStatus(){
		return this.isPaused;
	}
	public void setStatus(){
		this.isPaused=!this.isPaused;
	}

	public ArrayList<Plant> getPlantList(){
		return this.plantList;
	}
	public ArrayList<Zombie> getZombieList(){
		return this.zombieList;
	}
	public void setPlantList(ArrayList<Plant> plantList){
		this.plantList=plantList;
	}
	public void playBG(Clip clip) {
		try {
			clip.open(this.ais); 
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {}
		 
	}
	public void resetnotQuit(){
		this.notQuit=true;
	}
	public boolean checkSpace(Plant plant){
		for (int i=0;i<this.plantList.size();i+=1){
			if (plantList.get(i).getRectangle().intersects(plant.getRectangle()))
				return false;
		}return true;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.getBackgroundImage(), 0, 0, null);//backgroundimage
		Graphics2D g2 = (Graphics2D) g;
		//temporary
		// g2.setPaint(Color.GRAY);
		for (int i = 1; i < 10; i++) {
			if(true){
				int x = i * 100;
				g2.drawLine(x, 0, x, getSize().height);
			}
		}
		for (int i = 0; i < 5; i++) {
			if(true){
				int y = i * 100;
				g2.drawLine(0, y, getSize().width, y);
			}
		}
	}
	@Override
	public void run(){
		while(this.notDead&&this.notQuit){//change to no eat yet
			if(!this.isPaused)
				this.repaint();
		}
	}
}