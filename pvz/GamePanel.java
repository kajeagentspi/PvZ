package pvz;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	private int mxPos;
	private int myPos;
	private int level;
	private int zombiesKilled;
	private Stage stage;
	private PlantBoard pboard;
	private Menu menu;
	private ZombieCreator zombieCreator;
	public GamePanel(Menu menu){
		this.menu=menu;
		this.level=1;
		this.setPreferredSize(new Dimension(1000,600));
		this.setLayout(new BorderLayout());
		this.stage=new Stage();
		this.pboard=new PlantBoard(this);
		this.add(this.stage,BorderLayout.CENTER);
		this.add(this.pboard,BorderLayout.NORTH);
		this.requestFocus();
		this.addKeyListener(new PauseListener(this.stage));
		this.zombieCreator=new ZombieCreator(this.stage,this.level);
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
		this.zombieCreator.getTimer().start();
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
	public ZombieCreator getZombieCreator(){
		return this.zombieCreator;
	}
}