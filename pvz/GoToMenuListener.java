package pvz;
import java.awt.event.*;

public class GoToMenuListener implements ActionListener {
	private GamePanel game;
	public GoToMenuListener(GamePanel game){
		this.game=game;
	}
	public void actionPerformed(ActionEvent e){
		game.getStage().pause();
		game.getMenu().showMenu();
	}
}