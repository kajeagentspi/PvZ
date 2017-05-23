package pvz;
import java.net.*;
import java.awt.event.*;
import javax.swing.Timer;

public class WallNut extends Plant implements ActionListener{
	public WallNut(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_HIGH,"sprites/plants/Tallnut_Idle.gif",null,stage);
		this.actionSpd=100000;
		this.timer= new Timer(this.actionSpd,this);
	}
	public void shoot(){
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
		}
	}
}