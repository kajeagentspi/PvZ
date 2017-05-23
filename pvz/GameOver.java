package pvz;
import javax.swing.*;
public class GameOver extends JPanel{
	private JTextArea enterName;
	private JLabel score;
	private JButton gotoMenu;
	private GamePanel game;
	public GameOver(GamePanel game){
		this.enterName=new JTextArea("Entssser Name");
		this.score=new JLabel(Integer.toString(game.getScore()));
		this.gotoMenu=new JButton("Continue");
		this.game=game;
		this.gotoMenu.addActionListener(new ContinueToMenuListener(this.game,this.enterName.getText(),this.game.getScore()));
		this.add(enterName);
		this.add(new JLabel("Score: "));
		this.add(score);
		this.add(gotoMenu);
	}
}
