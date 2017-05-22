//To save and load plants get their variables that can change and save them instead of saving the Plant class
package pvz;
import java.io.Serializable;
public class ParticleVar implements Serializable{
	private int damage;
	private int speed;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private boolean isActive;
	private String imageLocation;
	private String audioLocation;
	private String type;

	public ParticleVar(int damage,int speed,int xPos,int yPos,int width,int height,boolean isActive,String imageLocation,String audioLocation,String type){
		this.damage=damage;
		this.speed=speed;
		this.xPos=xPos;
		this.yPos=yPos;
		this.width=width;
		this.height=height;
		this.isActive=isActive;
		this.imageLocation=imageLocation;
		this.audioLocation=audioLocation;
		this.type=type;
	}
	//getters
	public int getDamage(){
		return this.damage;
	}
	public int getSpeed(){
		return this.speed;
	}
	public int getXPos(){
		return this.xPos;
	}
	public int getYPos(){
		return this.yPos;
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public boolean getIsActive(){
		return this.isActive;
	}
	public String getType(){
		return this.type;
	}
	public String getImageLocation(){
		return this.imageLocation;
	}
	public String getAudioLocation(){
		return this.audioLocation;
	}
	
	//setters
	public void setXPos(int xPos){
		this.xPos=xPos;
	}
	public void setisActive(boolean isActive){
		this.isActive=isActive;
	}
}