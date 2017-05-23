package pvz;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 // for sounds
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Stage extends JPanel implements ActionListener{
	private int sun;
	private boolean isPaused;
	private boolean notDead;
	private boolean notQuit;
	private long clipTime;
	private AudioInputStream ais;
	private Clip clip;
	private ArrayList<Particle> particleList;
	private ArrayList<Zombie> zombieList;
	private ArrayList<Plant> plantList;
	private ArrayList<Sun> sunList;

	private Image background;
	private URL url;
	private Timer timer;
	public Stage(){
		this.setLayout(null);
		this.background=this.loadImage("sprites/menus/backdrop.png");
		this.setPreferredSize(new Dimension(1000, 500));
		this.notQuit=true;
		this.notDead=true;
		this.isPaused=false;
		try{ // for sound
			this.url = new URL("file:audio/pvzBG1.wav");
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);   
		}catch(Exception e){}
		this.playBG(this.clip); // for sound
		this.particleList=new ArrayList<Particle>();
		this.zombieList=new ArrayList<Zombie>();
		this.plantList=new ArrayList<Plant>();
		this.sunList=new ArrayList<Sun>();
		this.timer=new Timer(4,this);
		this.addZombie(new Zombie(1000,100,this));
		this.addZombie(new Zombie(1000,200,this));
		this.addZombie(new Zombie(1000,400,this));
		this.addSun(new Sun(500,400,this));

		this.timer.start();
	}
	//getters/checkers
	public boolean getStatus(){
		return this.isPaused;
	}
	public boolean checkSpace(Plant plant){
		for (int i=0;i<this.plantList.size();i+=1){
			if (plantList.get(i).getRectangle().intersects(plant.getRectangle()))
				return false;
		}return true;
	}
	public boolean zombieCheck(int xPos,int yPos){
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(zombieList.get(i).getRectangle().intersects(new Rectangle(xPos,yPos,(1000-xPos),100)))
					return true;
			}
		}return false;
	}
	public ArrayList<Plant> getPlantList(){
		return this.plantList;
	}
	public ArrayList<Zombie> getZombieList(){
		return this.zombieList;
	}
	public ArrayList<Particle> getParticleList(){
		return this.particleList;
	}
	public void setPlantList(ArrayList<Plant> plantList){
		this.plantList=plantList;
	}
	private Image loadImage(String imageLocation){
		try{
			return Toolkit.getDefaultToolkit().getImage(new URL("file:"+imageLocation));
		} catch(Exception e){}	
		return null;
	}
	//adds plant
	public void addPlant(Plant plant){
		this.plantList.add(plant);
		this.add(plant);
	}
	//adds particles
	public void addParticle(Particle particle){
		this.particleList.add(particle);
		this.add(particle);
	}
	//adds zombies
	public void addZombie(Zombie zombie){
		this.zombieList.add(zombie);
		Thread threadTemp=new Thread(zombie);
		this.add(zombie);
		threadTemp.start();
	}
	public void addSun(Sun sun){
		this.sunList.add(sun);
		this.add(sun);
	}
	//removes dead zombie
	public void clearDeadZombie(Zombie zombie){
		this.remove(zombie);
		this.zombieList.remove(zombie);

	}
	//removes particles that hit
	public void clearDeadParticle(Particle particle){
		this.remove(particle);
		this.particleList.remove(particle);

	}
	//removes dead plants
	public void clearDeadPlants(Plant plant){
		this.remove(plant);
		this.plantList.remove(plant);
	}
	public void clearDeadSuns(Sun sun){
		this.remove(sun);
		this.sunList.remove(sun);
	}
	//pauses the game
	//sets the suspenFlag of all elements to true
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
	//resumes the game
	//sets the suspenFlag of all elements to false
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
	//toggle for status
	public void setStatus(){
		this.isPaused=!this.isPaused;
	}
	//plays the background music
	public void playBG(Clip clip) {
		try {
			clip.open(this.ais); 
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {}	 
	}
	//after loading the game revert the notQuit status to true
	public void resetnotQuit(){
		this.notQuit=true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, null);//backgroundimage
		Graphics2D g2 = (Graphics2D) g;
		//draws the bars
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
	// public void moveZombies(){
	// 	for(int i=0;i<zombieList.size();i+=1){
	// 		zombieList.get(i).move();
	// 	}
	// }
	public void moveParticles(){
		for(int i=0;i<this.particleList.size();i+=1){
			this.particleList.get(i).move();
		}
	}
	public void shootPlants(){
		for(int i=0;i<this.plantList.size();i+=1){
			this.plantList.get(i).shoot();
		}
	}
	public void addSun(){
		this.sun+=25;
		System.out.println(this.sun);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer&&this.notQuit&&!this.isPaused){
			this.shootPlants();
			this.moveParticles();
			this.repaint();// this will call at every 1 second
		}
	}
}