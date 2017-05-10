
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class Stage extends JPanel{
	private ArrayList<Particle> particleList;
	private ArrayList<Zombie> zombieList;
	private ArrayList<Plant> plantList;
	private ArrayList<Thread> particleThreads;
	private ArrayList<Thread> zombieThreads;
	private ArrayList<Thread> plantThreads;
	private boolean isPaused;
	private int timer;

	public Stage(){
		this.setLayout(null);
		this.setOpaque(true);
		this.isPaused=false;
		particleList=new ArrayList<Particle>();
		zombieList=new ArrayList<Zombie>();
		plantList=new ArrayList<Plant>();
		particleThreads=new ArrayList<Thread>();
		zombieThreads=new ArrayList<Thread>();
		plantThreads=new ArrayList<Thread>();
		
		// this.addPlant(new PeaShooter(100,0,this));
		this.addPlant(new SnowPea(0,0,this));

		this.addZombie(new Zombie(450,300,this));
		this.addPlant(new CherryBomb(300,300,this));


		this.addPlant(new WallNut(700,0,this));
		// this.addPlant(new PeaShooter(0,100,this));
		this.addZombie(new Zombie(900,0,this));
		this.addZombie(new Zombie(900,100,this));
		this.addZombie(new Zombie(1000,0,this));



		this.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(900, 500));
	  	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void addPlant(Plant plant){
		this.plantList.add(plant);
		Thread threadTemp=new Thread(plant);
		this.add(plant);
		Toolkit.getDefaultToolkit().sync();
		threadTemp.start();
		this.plantThreads.add(threadTemp);
	}

	public void addParticle(Particle particle){
		this.particleList.add(particle);
		Thread threadTemp=new Thread(particle);
		this.add(particle);
		Toolkit.getDefaultToolkit().sync();
		threadTemp.start();
		this.particleThreads.add(threadTemp);
	}

	public void addZombie(Zombie zombie){
		this.zombieList.add(zombie);
		Thread threadTemp=new Thread(zombie);
		this.add(zombie);
		Toolkit.getDefaultToolkit().sync();
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
					this.repaint();
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
		this.repaint();

		// if(zombieList.size()!=0){
		// 	for(int i=this.zombieList.size()-1;i>=0;i-=1){
		// 		if(!this.zombieList.get(i).getStatus()){
		// 			this.remove(zombieList.get(i));
		// 			this.zombieThreads.remove(i);
		// 			this.zombieList.remove(i);
		// 			this.repaint();
		// 		}
		// 	}
		// }
	}
	public void clearDeadParticle(Particle particle){
		this.remove(particle);
		this.particleList.remove(particle);
		this.repaint();

		// if(particleList.size()!=0){
		// 	for(int i=this.particleList.size()-1;i>=0;i-=1){
		// 		if(!this.particleList.get(i).getStatus()){
		// 			this.remove(particleList.get(i));
		// 			this.particleThreads.remove(i);
		// 			this.particleList.remove(i);
		// 			this.repaint();
		// 		}
		// 	}
		// }
	}
	public void clearDeadPlants(Plant plant){
		this.remove(plant);
		this.plantList.remove(plant);
		this.repaint();

		// if(plantList.size()!=0){
		// 	for(int i=this.plantList.size()-1;i>=0;i-=1){
		// 		if(!this.plantList.get(i).getStatus()){
		// 			this.remove(plantList.get(i));
		// 			this.plantThreads.remove(i);
		// 			this.plantList.remove(i);
		// 			this.repaint();
		// 		}	
		// 	}
		// }
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
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GRAY);
		for (int i = 1; i < 9; i++) {
		   int x = i * 100;
		   g2.drawLine(x, 0, x, getSize().height);
		}
		for (int i = 1; i < 5; i++) {
		   int y = i * 100;
		   g2.drawLine(0, y, getSize().width, y);
		}
	}
}