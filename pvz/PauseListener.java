package pvz;
import java.awt.event.*;

public class PauseListener implements KeyListener, ActionListener{
	private Stage stage;
	private GamePanel game;
	public PauseListener(GamePanel game){
		this.game=game;
		this.stage=game.getStage();
	}
	public void actionPerformed(ActionEvent e){
		if(!this.stage.getStatus()&&this.stage.getIsAlive()){
			this.stage.setStatus();
			this.stage.pause();
			this.game.getZombieCreator().getTimer().stop();
			this.game.getSunCreator().getTimer().stop();
		}
		else if(this.stage.getStatus()&&this.stage.getIsAlive()){
			this.stage.setStatus();
			this.stage.resume();
			this.game.getZombieCreator().getTimer().start();
			this.game.getSunCreator().getTimer().start();
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27){   //this is the code for the escape key
			if(!this.stage.getStatus()){
				this.stage.setStatus();
				this.stage.pause();
			}
			else if(this.stage.getStatus()){
				this.stage.setStatus();
				this.stage.resume();
			}
		}
	}
	public void keyReleased(KeyEvent e) {}
}