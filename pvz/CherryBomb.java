package pvz;
import java.awt.Rectangle;
import java.net.*;
import java.awt.event.*;
import javax.swing.Timer;

public class CherryBomb extends Plant implements ActionListener{
	public CherryBomb(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_INFINITE,"sprites/plants/CherryBomb_Idle.gif","audio/CherryBomb.wav",stage);
		this.actionSpd=2000;
		this.timer= new Timer(this.actionSpd,this);
	}
	public void shoot(){
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==timer){
			Rectangle explosion=new Rectangle(this.xPos-100,this.yPos-100,300,300);
			for(int i=stage.getZombieList().size()-1; i>=0;i-=1){
				if(explosion.intersects(stage.getZombieList().get(i).getRectangle()))
					stage.getZombieList().get(i).damaged(DAMAGE_HEAVY);
				this.soundComponent(this.clip);
			}this.setHP(TOUGHNESS_INFINITE);
    	}
	}
}