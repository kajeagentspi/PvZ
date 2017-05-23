package pvz;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.*;

public class Repeater extends Plant implements ActionListener{
	private boolean firstshot;
	public Repeater(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/Chomper_Idle.gif","audio/Repeater.wav",stage);
		this.actionSpd=1500;
		this.timer=new Timer(this.actionSpd,this);
		this.firstshot=true;
	}

	public void shoot(){ //create peas until dead
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			if(firstshot){
				this.timer.setDelay(500);
				this.firstshot=false;
			}else{
				this.firstshot=true;
				this.timer.restart();
			}
			if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag&&this.isAlive) {
				this.soundComponent(this.clip);
				this.changeIcon("sprites/plants/Repeater_Fire.gif");
				this.stage.addParticle(new Pea(this.xPos+25,this.yPos+12,stage));
				this.changeIcon("sprites/plants/Repeater_Idle.gif");
			}
    	}
	}
}