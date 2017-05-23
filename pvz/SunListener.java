package pvz;
import java.awt.event.*;
import java.awt.Rectangle;
import java.util.ArrayList;
public class SunListener implements MouseListener{
	private GamePanel game;
	private ArrayList<Sun> sunList;
	private PlantBoard pboard;
	private Stage stage;
	private int mxPos;
	private int myPos;
	public SunListener(GamePanel game){
		this.game=game;
		this.sunList=game.getStage().getSunList();
		this.pboard=game.getPboard();
		this.stage=game.getStage();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		this.mxPos=game.getmxPos();
		this.myPos=game.getmyPos();
		for(int i=0;i<sunList.size();i+=1){
			Rectangle rect=new Rectangle(this.mxPos,this.myPos-100,1,1);
			if(this.sunList.get(i).getRectangle().intersects(rect)&&!this.stage.getStatus()){
				this.game.addSun(25);
				this.stage.clearDeadSuns(sunList.get(i));
			}
		}
		
	}
}