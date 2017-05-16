import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
 // for sounds
import java.net.URL;
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
    
    private URL url;
    private Clip clip;
    private AudioInputStream ais;
	private Image background;
	public Stage(){
		this.setLayout(null);
		this.isPaused=false;
		particleList=new ArrayList<Particle>();
		zombieList=new ArrayList<Zombie>();
		plantList=new ArrayList<Plant>();
		particleThreads=new ArrayList<Thread>();
		zombieThreads=new ArrayList<Thread>();
		plantThreads=new ArrayList<Thread>();
		this.addPlant(new PeaShooter(100,100,this));
		this.addZombie(new Zombie(450,300,this));
		this.addZombie(new Zombie(900,100,this));
		this.addPlant(new CherryBomb(300,300,this));
		try{
			this.background = ImageIO.read(new File("11.png"));//background image
             // for sound
            this.url = new URL("/Users/allenoponcedeleon/Documents/PvZ/pvzBG1.wav");
            this.clip = AudioSystem.getClip();
            this.ais = AudioSystem.getAudioInputStream(this.url);   
		}catch(Exception e){}
		// this.addPlant(new WallNut(700,200,this));
		this.addZombie(new Zombie(900,200,this));
		this.setPreferredSize(new Dimension(1000, 600));
        this.playBG(this.clip); // for sound
        
    
            
		
	}

	public void addPlant(Plant plant){
		this.plantList.add(plant);
		Thread threadTemp=new Thread(plant);
		this.add(plant);
		threadTemp.start();
		this.plantThreads.add(threadTemp);
	}

	public void addParticle(Particle particle){
		this.particleList.add(particle);
		Thread threadTemp=new Thread(particle);
		this.add(particle);
		threadTemp.start();
		this.particleThreads.add(threadTemp);
	}

	public void addZombie(Zombie zombie){
		this.zombieList.add(zombie);
		Thread threadTemp=new Thread(zombie);
		this.add(zombie);
		threadTemp.start();
		this.zombieThreads.add(threadTemp);
	}

	public boolean zombieCheck(int xPos,int yPos){
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(zombieList.get(i).getRectangle().intersects(new Rectangle(xPos,yPos,900,100)))
					return true;
			}
		}return false;
	}

	public void particleZombieColissionCheck(Particle particle){
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(particle.getRectangle().intersects(zombieList.get(i).getRectangle())){
					zombieList.get(i).damaged(particle.getDamage());
					particle.setStatus();
					
					break;
				}
			}
		}
	}

	public Plant zombiePlantColissionCheck(Zombie zombie){
		if(plantList.size()!=0){
			for(int i=0;i<plantList.size();i+=1){
				if(zombie.getRectangle().intersects(plantList.get(i).getRectangle())&&zombie.getStatus()){
					return plantList.get(i);
				}
			}
		}return null;
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
		}
	}

	public void resume(){
		for(int i=0;i<zombieList.size();i+=1){
			zombieList.get(i).resume();
		}for(int i=0;i<particleList.size();i+=1){
			particleList.get(i).resume();
		}for(int i=0;i<plantList.size();i+=1){
			plantList.get(i).resume();
		}
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
    
    public void playBG(Clip clip) {
        try {
            clip.open(this.ais); 
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception e) {}
         
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);//backgroundimage
		Graphics2D g2 = (Graphics2D) g;
		//temporary
		// g2.setPaint(Color.GRAY);
		for (int i = 1; i < 10; i++) {
			if(true){
				int x = i * 100;
				g2.drawLine(x, 0, x, getSize().height);
			}
		}
		for (int i = 1; i < 6; i++) {
			if(true){
				int y = i * 100;
				g2.drawLine(0, y, getSize().width, y);
			}
		}
	}
	@Override
	public void run(){
		while(true){
			this.repaint();
		}
	}
}