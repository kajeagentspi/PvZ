package pvz;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	private int mxPos;
	private int myPos;
	private Stage stage;
	private PlantBoard pboard;
	private Menu menu;
	public GamePanel(Menu menu){
		this.menu=menu;
		this.setPreferredSize(new Dimension(1000,600));
		this.setLayout(new BorderLayout());
		this.stage=new Stage();
		this.pboard=new PlantBoard(this);
		this.add(this.stage,BorderLayout.CENTER);
		this.add(this.pboard,BorderLayout.NORTH);
		this.requestFocus();
		this.addKeyListener(new PauseListener(this.stage));
		//get coords
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent me){
				mxPos= me.getX();
				myPos= me.getY();
				pboard.showCoords(mxPos,myPos);
			}
		});
		this.addMouseListener(new PlanterListener(this));
		this.addMouseListener(new SunListener(this));
	}
	//getters
	public Menu getMenu(){
		return this.menu;
	}
	public PlantBoard getPboard(){
		return this.pboard;
	}
	public Stage getStage(){//returns stage
		return this.stage;
	}
	public int getmxPos(){
		return this.mxPos;
	}
	public int getmyPos(){
		return this.myPos;
	} 
}