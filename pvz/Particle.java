package pvz;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.Timer;

public class Particle extends Sprite implements ActionListener{
	protected int damage;
	protected boolean isActive;
	protected int speed;
	protected Stage stage;
	protected ParticleVar particlevar;
	private Timer timer;
	public Particle(int xPos, int yPos, int width, int height, int damage, int speed, String imageLocation, String audiolocation, String type, Stage stage){
		super(xPos, yPos, width, height, imageLocation, audiolocation);
		this.damage=damage;
		this.speed=speed;
		this.timer=new Timer(this.speed,this);
		this.isActive=true;
		this.stage=stage;
		this.particlevar=new ParticleVar(damage,speed,xPos,yPos,width,height,isActive,imageLocation,audioLocation,type);
	}
	public Particle(ParticleVar particlevar, Stage stage){
		super(particlevar.getXPos(),particlevar.getYPos(),particlevar.getWidth(),particlevar.getHeight(),particlevar.getImageLocation(),particlevar.getAudioLocation());
		this.damage=particlevar.getDamage();
		this.speed=particlevar.getSpeed();
		this.timer=new Timer(this.speed,this);
		this.isActive=particlevar.getIsActive();
		this.stage=stage;
		this.particlevar=particlevar;
		this.suspendFlag=true;//paused during creation
	}
	public int getDamage(){//return damge of particle
		return this.damage;
	}
	public synchronized boolean getStatus(){//para malaman kung buburahin na
		return this.isActive;
	}
	protected void setStatus(){//change the status of particle to be deleted
		this.isActive=false;
		this.particlevar.setisActive(false);
		this.stage.clearDeadParticle(this);
	}

	protected void move(){//move pea's xpos by 1
		this.timer.start();
	}
	protected synchronized void colissionCheck(ArrayList<Zombie> zombieList){//check if zombie is hit
		if(zombieList.size()!=0){
			for(int i=0;i<zombieList.size();i+=1){
				if(this.getRectangle().intersects(zombieList.get(i).getRectangle())){
					this.soundComponent(this.clip);
					zombieList.get(i).damaged(this.getDamage());
					this.setStatus();
					break;
				}
			}
		}
	}
	public ParticleVar getParticleVar(){
		return this.particlevar;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			if(this.isActive){
				this.xPos+=1;
				this.particlevar.setXPos(this.xPos);
				this.updateRectangle();
				this.colissionCheck(stage.getZombieList());
				if(this.xPos>this.width+1000){
					this.setStatus();
				}
			}
    	}
	}
}