package pvz;
import java.awt.event.*;
public class GoToMenuListener implements ActionListener {
	private GamePanel game;
	public GoToMenuListener(GamePanel game){
		this.game=game;
	}
	public void actionPerformed(ActionEvent e){
		if(!game.getStage().getStatus()){
			game.getStage().setStatus();
			game.getStage().pause();
		}game.getStage().setStatus();
		game.getMenu().saveGame();
		game.getMenu().showMenu();
	}
}