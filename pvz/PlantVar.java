package pvz;
import java.io.Serializable;
public class PlantVar implements Serializable{
	private int hp;
	private int actionSpd;
	private boolean isAlive;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private String location;
	private String type;

	public PlantVar(int hp,int actionSpd,boolean isAlive,int xPos,int yPos,int width,int height,String location,String type){
		this.hp=hp;
		this.actionSpd=actionSpd;
		this.isAlive=isAlive;
		this.xPos=xPos;
		this.yPos=yPos;
		this.width=width;
		this.height=height;
		this.location=location;
		this.type=type;
	}

	public String getType(){
		return this.type;
	}
	public void setHP(int hp){
		this.hp=hp;
	}
	public void setisAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	public void setLocation(String location){
		this.location=location;
	}
	public int getHP(){
		return this.hp;
	}
	public int getActionSpd(){
		return this.actionSpd;
	}
	public boolean getIsAlive(){
		return this.isAlive;
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
	public String getLocation(){
		return this.location;
	}
}