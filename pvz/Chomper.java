package pvz;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.Timer;

public class Chomper extends Plant implements ActionListener{
	public boolean active;
	public Chomper(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/Chomper_Idle.gif","audio/Chomper.wav",stage);
		this.actionSpd=1500;
		this.timer= new Timer(this.actionSpd,this);
		this.active=false;
	}
	public void shoot(){
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			this.active=true;
			this.timer.setDelay(1500);
			if(this.active){
				Rectangle biterange=new Rectangle(this.xPos,this.yPos,200	,100);
				for(int i=stage.getZombieList().size()-1; i>=0;i-=1){
					if(biterange.intersects(stage.getZombieList().get(i).getRectangle())){
						this.changeIcon("sprites/plants/Chomper_Eating.gif");
						stage.getZombieList().get(i).damaged(DAMAGE_HEAVY);
						this.soundComponent(this.clip);
						this.timer.setDelay(15000);
						this.active=false;
						break;
					}
				}
			}
		}
	}
}