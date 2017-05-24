package pvz;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class GameOver extends JPanel{
	private JTextField enterName;
	private JLabel score;
	private JButton gotoMenu;
	private GamePanel game;
	private ArrayList<Score> scoreboard;
	private int scoreInt;
	public GameOver(GamePanel game){
		this.scoreboard=this.loadScoreBoard();
		this.scoreInt=game.getScore();
		this.enterName=new JTextField("Enter Name");
		this.score=new JLabel(Integer.toString(game.getScore()));
		this.gotoMenu=new JButton("Continue");
		this.game=game;
		this.gotoMenu.addActionListener(new ContinueToMenuListener(this));
		this.add(enterName);
		this.add(new JLabel("Score: "));
		this.add(score);
		this.add(gotoMenu);
	}
	public void updateScoreBoard(Score scoredata){
		if(this.scoreboard.size()>0){
			for(int i=0;i<this.scoreboard.size();i++){
				if(this.scoreboard.get(i).getScore()<=scoredata.getScore()){
					this.scoreboard.add(i,scoredata);
					break;
				}
			}
		}else{
			this.scoreboard.add(scoredata);
		}
		this.saveScoreBoard();
	}
	public ArrayList<Score> loadScoreBoard(){
		try{
			FileInputStream fis = new FileInputStream("scores.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Score> scoreBoard=new ArrayList<Score>();
			ArrayList<?> objectList = (ArrayList<?>) ois.readObject();
			if (objectList.size()>0){
				for(int i=0;i<objectList.size();i+=1){;
					Object o=objectList.get(i);
					if(o instanceof Score){
						scoreBoard.add((Score)o);
					}
				}
			}
			ois.close();
			return scoreBoard;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Loading Error");
		}
		return new ArrayList<Score>();
	}

	public void saveScoreBoard(){
		try{
			ObjectOutputStream objectOut=new ObjectOutputStream(new FileOutputStream("scores.sav"));
			objectOut.writeObject(this.scoreboard);
			objectOut.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	public GamePanel getGame(){
		return this.game;
	}
	public String getName(){
		return this.enterName.getText();
	}
	public int getScore(){
		return this.scoreInt;
	}
}
