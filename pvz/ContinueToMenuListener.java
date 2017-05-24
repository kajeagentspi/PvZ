package pvz;
import java.awt.event.*;

public class ContinueToMenuListener implements ActionListener {
	private GameOver gameOver;
	private String name;
	private int score;
	public ContinueToMenuListener(GameOver gameOver){
		this.gameOver=gameOver;
		this.name=gameOver.getName();
		this.score=gameOver.getScore();
	}
	public void actionPerformed(ActionEvent e){
		this.name=gameOver.getName();
		this.gameOver.getGame().getMenu().showMenu();
		this.gameOver.updateScoreBoard(new Score(name,score));

		System.out.println("Saved"+name+score);
	}
}