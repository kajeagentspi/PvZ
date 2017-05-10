public abstract class Plant extends Sprite implements Runnable{
	protected int hp;
	protected int actionSpd;
	protected Stage stage;
	protected boolean isAlive;
	public Plant(int xPos, int yPos,int width,int height, String filename,Stage stage,int hp){
		super(xPos,yPos,width,height,filename);
		this.stage=stage;
		this.isAlive=true;
		this.hp=hp;
	}

	public int getHP(){//returns hp
		return this.hp;
	}

	public void setHP(int dmg){//sets damage
		this.hp-=dmg;
		System.out.println("P HP: "+this.hp);
		if(this.hp<=0){
			this.setStatus();
			System.out.println("Plant dead");
			stage.clearDeadPlants(this);
		}
	}
	public boolean getStatus(){
		return this.isAlive;
	}
	public void setStatus(){
		this.isAlive=false;
	}
	public int getActionSpd(){
		return this.actionSpd;
	}
	
}