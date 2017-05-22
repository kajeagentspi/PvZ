package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.*;

public class PeaShooter extends Plant implements ActionListener{
	public PeaShooter(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/Peashooter_Idle.gif","audio/peaShooter.wav",stage);
		this.actionSpd=1500;
		this.timer=new Timer(this.actionSpd,this);
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "PeaShooter",imageLocation ,audioLocation);
	}
	public PeaShooter(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
		this.timer=new Timer(this.actionSpd,this);
	}

	public void shoot(){ //create peas until dead
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag&&this.isAlive) {
				this.soundComponent(this.clip);
				this.changeIcon("sprites/plants/Peashooter_Fire.gif");
				this.stage.addParticle(new Pea(this.xPos+25,this.yPos+12,stage));
				this.changeIcon("sprites/plants/Peashooter_Idle.gif");
			}
    	}
	}
}