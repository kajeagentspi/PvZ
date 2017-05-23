package pvz;
import java.awt.event.*;

public class PauseListener implements KeyListener{
	private Stage stage;
	public PauseListener(Stage stage){
		this.stage=stage;
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