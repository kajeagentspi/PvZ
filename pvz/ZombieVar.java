//To save and load plants get their variables that can change and save them instead of saving the Plant class
package pvz;
import java.io.Serializable;
public class ZombieVar implements Serializable{
	private int damage;
	private int speed;
	private int hp;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private boolean isAlive;
	private boolean isEating;
	private boolean isSlowed;
	private String imageLocation;
	private String audioLocation;
	private String type;

	public ZombieVar(int damage,int speed, int hp,int xPos,int yPos,int width,int height,boolean isAlive,boolean isEating,boolean isSlowed,String imageLocation,String audioLocation,String type){
		this.damage=damage;
		this.speed=speed;
		this.hp=hp;
		this.xPos=xPos;
		this.yPos=yPos;
		this.width=width;
		this.height=height;
		this.isAlive=isAlive;
		this.isEating=isEating;
		this.isSlowed=isSlowed;
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
	public int getHP(){
		return this.hp;
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
	public boolean getIsAlive(){
		return this.isAlive;
	}
	public boolean getIsEating(){
		return this.isEating;
	}
	public boolean getIsSlowed(){
		return this.isSlowed;
	}
	public String getImageLocation(){
		return this.imageLocation;
	}
	public String getAudioLocation(){
		return this.audioLocation;
	}
	public String getType(){
		return this.type;
	}
	//setters
	public void setXPos(int xPos){
		this.xPos=xPos;
	}
	public void setHP(int hp){
		this.hp=hp;
	}
	public void setIsAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	public void setIsEating(boolean isEating){
		this.isEating=isEating;
	}
	public void setIsSlowed(boolean isSlowed){
		this.isSlowed=isSlowed;
	}
	public void setImageLocation(String imageLocation){
		this.imageLocation=imageLocation;
	}
}