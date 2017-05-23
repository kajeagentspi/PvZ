package pvz;
import java.awt.Rectangle;
import java.net.*;
import java.awt.event.*;
import javax.swing.Timer;

public class PotatoMine extends Plant implements ActionListener{
	private boolean active;
	public PotatoMine(int xPos, int yPos, Stage stage){
		super(xPos,yPos,100,100,TOUGHNESS_NORMAL,"sprites/plants/PotatoMine_Deactivated.gif","audio/PotatoMine.wav",stage);
		this.actionSpd=15000;
		this.timer= new Timer(this.actionSpd,this);
		this.plantvar=new PlantVar(hp, actionSpd, xPos, yPos, width, height, isAlive, cliptime, "PotatoMine", imageLocation ,audioLocation);
		this.active=false;
	}

	public PotatoMine(PlantVar plantvar,Stage stage){
		super(plantvar,stage);
		this.timer= new Timer(this.actionSpd,this);
	}
	public void shoot(){
		this.timer.start();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			this.active=true;
			if(this.active){
				this.changeIcon("sprites/plants/PotatoMine_Activating.gif");
				for(int i=this.stage.getZombieList().size()-1; i>=0;i-=1){
					if(this.getRectangle().intersects(stage.getZombieList().get(i).getRectangle())){
						this.stage.getZombieList().get(i).damaged(DAMAGE_HEAVY);
						this.soundComponent(this.clip);
						this.setHP(TOUGHNESS_NORMAL);
					}
				}
			}
		}
		
	}
}