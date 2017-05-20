package pvz;
import java.util.Random;
class ZombieCreator implements Runnable{
	Stage stage;
	Random r=new Random();
	public ZombieCreator(Stage stage){
		this.stage=stage;
	}
	public void run(){
		try{
			Thread.sleep(10000); 
		}catch(Exception e){}
		stage.addZombie(new Zombie(1100,r.nextInt(5)*100,stage));
		try{
			Thread.sleep(10000); 
		}catch(Exception e){}
		stage.addZombie(new Zombie(1100,r.nextInt(5)*100,stage));
		try{
			Thread.sleep(10000); 
		}catch(Exception e){}
		stage.addZombie(new Zombie(1100,r.nextInt(5)*100,stage));
		stage.addZombie(new Zombie(1100,r.nextInt(5)*100,stage));
	}
}