package pvz;
import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

public  class ZombieCreator implements ActionListener{
	private Stage stage;
	private int level;
	private Timer timer;
	private Random r=new Random();
	private final int REGULAR_ZOMBIE = 1;
	private final int CONEHEAD_ZOMBIE = 2;
	private final int BUCKETHEAD_ZOMBIE = 3;

	public ZombieCreator(Stage stage,int level){
		this.stage=stage;
		this.level=level;
		this.timer=new Timer(8000,this);
	}


	public void chooseZombie(int type) {
		switch (type) {
			case REGULAR_ZOMBIE: stage.addZombie(new Zombie(1100,r.nextInt(5)*100,stage));
				break;
			case CONEHEAD_ZOMBIE: stage.addZombie(new Zombie(1100,r.nextInt(5)*100, stage));
				break;
			case BUCKETHEAD_ZOMBIE: stage.addZombie(new Zombie(1100,r.nextInt(5)*100, stage));
				break;
			default: break;
		}
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer&&this.stage.getIsAlive()){
			if(this.level<=5||this.level>5)//1-5
				this.chooseZombie(REGULAR_ZOMBIE);
			if(this.level>5&&this.level<=10||this.level>10)//6-10
				this.chooseZombie(CONEHEAD_ZOMBIE);
			if(this.level>10)
				this.chooseZombie(BUCKETHEAD_ZOMBIE);
		}
	}
	public void updateLevel(int level){
		this.level=level;
	}
	public Timer getTimer(){
		return this.timer;
	}

}