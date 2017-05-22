//To save and load plants get their variables that can change and save them instead of saving the Plant class
package pvz;
import java.io.Serializable;
public class PlantVar implements Serializable{
	private int hp;
	private int actionSpd;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private boolean isAlive;
	private long clipTime;
	private String type;
	private String imageLocation;
	private String audioLocation;

	public PlantVar(int hp,int actionSpd,int xPos,int yPos,int width,int height,boolean isAlive,long clipTime,String type,String imageLocation,String audioLocation){
		this.hp=hp;
		this.actionSpd=actionSpd;
		this.xPos=xPos;
		this.yPos=yPos;
		this.width=width;
		this.height=height;
		this.isAlive=isAlive;
		this.clipTime=clipTime;
		this.type=type;
		this.imageLocation=imageLocation;
		this.audioLocation=audioLocation;
	}
	//getters
	public int getHP(){
		return this.hp;
	}
	public int getActionSpd(){
		return this.actionSpd;
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
	public long getClipTime(){
		return this.clipTime;
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
	public void setHP(int hp){
		this.hp=hp;
	}
	public void setisAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	public void setClipTime(long clipTime){
		this.clipTime=clipTime;
	}
	public void setLocation(String imageLocation){
		this.imageLocation=imageLocation;
	}
	
}