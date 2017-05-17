import java.awt.*;
import javax.swing.*;
import java.io.*;
public class GamePanel extends JPanel implements Serializable{
	private Stage stage;
	private PlantBoard pboard; 
	public GamePanel(){
		this.setLayout(new BorderLayout());
		stage=new Stage();
		pboard=new PlantBoard(stage);
		this.add(stage,BorderLayout.CENTER);
		this.add(pboard,BorderLayout.NORTH);
	}
	public Stage getStage(){//returns stage
		return this.stage;
	}
	public PlantBoard getPboard(){
		return this.pboard;
	}
}