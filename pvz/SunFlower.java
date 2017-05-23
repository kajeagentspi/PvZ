package pvz;
import java.awt.event.*;
import javax.swing.Timer;

public class SunFlower extends Plant implements ActionListener{
	public SunFlower(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/SunFlower_Idle.gif","audio/SunFlower.wav",stage);
		this.actionSpd=10000;
		this.timer=new Timer(this.actionSpd,this);
	}
	public void shoot(){
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==timer){
			this.stage.addSun(new Sun(this.xPos,this.yPos,stage));
		}
	}
}