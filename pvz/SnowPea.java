package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.*;

public class SnowPea extends Plant implements ActionListener{
	public SnowPea(int xPos, int yPos, Stage stage){
		super(xPos, yPos, 100, 100, TOUGHNESS_NORMAL, "sprites/plants/SnowPea_Idle.gif", "audio/snowPea.wav", stage);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
		this.actionSpd=1500;
		this.timer=new Timer(this.actionSpd,this);
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "SnowPea",imageLocation ,audioLocation);
	}
	public SnowPea(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
		this.timer=new Timer(this.actionSpd,this);
	}
	public void shoot(){ //create peas until dead
		timer.start();
	}
	public void actionPerformed(ActionEvent e){ //create peas until dead
		if(e.getSource()==timer){
			if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag) {
				System.out.println(":lol");
				stage.addParticle(new FrozenPea(this.xPos+25,this.yPos+12,stage));
				this.soundComponent(this.clip);
			}
		}
	}
	// @Override
	// public void run(){
	// 	while(this.isAlive){
	// 		try{
	// 			synchronized(this){
	// 				while(this.suspendFlag){
	// 					wait();
	// 				}
	// 			}
	// 		}catch (InterruptedException e){}
	// 		this.shoot();
	// 		try {
	// 			Thread.sleep(this.actionSpd);
	// 		}catch (Exception e){}
	// 	}
		
	// }

}