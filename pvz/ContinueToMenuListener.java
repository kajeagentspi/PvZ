package pvz;
import java.awt.event.*;

public class ContinueToMenuListener implements ActionListener {
	private GamePanel game;
	private String name;
	private int score;
	public ContinueToMenuListener(GamePanel game,String name,int score){
		this.game=game;
		this.name=name;
		this.score=score;
	}
	public void actionPerformed(ActionEvent e){
		this.game.getMenu().showMenu();
		this.game.getMenu().saveScores(name,score);
	}
}