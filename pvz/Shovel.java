package pvz;
import javax.swing.*;
import java.awt.event.*;
public class Shovel extends Plant implements ActionListener{
	public Shovel(int xPos, int yPos,Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_HIGH,"sprites/plants/Shovel.png","audio/Shovel.wav",stage);
		this.actionSpd=100;
		this.timer=new Timer(this.actionSpd,this);
	}
	public void shoot(){ //create peas until dead
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			for(int i=0; i<this.stage.getPlantList().size();i+=1){
				if(this.getRectangle().intersects(this.stage.getPlantList().get(i).getRectangle())&&!(stage.getPlantList().get(i) instanceof Shovel)){
					this.stage.getPlantList().get(i).setHP(DAMAGE_HEAVY);
					this.soundComponent(this.clip);
				}
					this.setHP(DAMAGE_HEAVY);
			}
    	}
	}
}