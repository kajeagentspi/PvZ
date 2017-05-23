package pvz;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	private int mxPos;
	private int myPos;
	private int level;
	private int score;
	private int sun;
	private int zombiesKilled;
	private Stage stage;
	private PlantBoard pboard;
	private Menu menu;
	private ZombieCreator zombieCreator;
	private SunCreator sunCreator;
	public GamePanel(Menu menu){
		this.menu=menu;
		this.level=1;
		this.sun=50;
		this.setPreferredSize(new Dimension(1000,600));
		this.setLayout(new BorderLayout());
		this.stage=new Stage(this);
		this.pboard=new PlantBoard(this);
		this.add(this.stage,BorderLayout.CENTER);
		this.add(this.pboard,BorderLayout.NORTH);
		this.requestFocus();
		this.addKeyListener(new PauseListener(this));
		this.zombieCreator=new ZombieCreator(this.stage,this.level);
		this.sunCreator=new SunCreator(this.stage);

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
		this.sunCreator.getTimer().start();
	}
	//getters
	public int getLevel(){
		return this.level;
	}
	

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
	public SunCreator getSunCreator(){
		return this.sunCreator;
	}
	public void addSun(int sun){
		this.sun+=sun;
		this.pboard.setSun(this.sun);
	}
	public void minusSun(int sun){
		this.sun-=sun;
		this.pboard.setSun(this.sun);
	}
	public int getSun(){
		return this.sun;
	}
	public int getScore(){
		return this.score;
	}
	public void setScore(int score){
		this.score+=score;
		this.pboard.setScore(this.score);
		this.setLevel();
	}
	public void setLevel(){
		this.level=(this.score/10)+1;
		this.pboard.setLevel(this.level);
		this.zombieCreator.updateLevel(this.level);
	}
}