package pvz;
import java.awt.event.*;

public class Sun extends Sprite{
	private boolean isClicked;
	private boolean isEntered;
	private Sun sun;
	public Sun(int xPos, int yPos ,Stage stage){
		super(xPos, yPos, 100, 100, "sprites/plants/Sun.gif", "audio/plants/Sun.gif");
		this.sun=this;
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				if(isEntered){
					stage.addSun();
					stage.clearDeadSuns(sun);
				}
			}
			public void mouseEntered(MouseEvent e){
				isEntered=true;
			}
			public void mouseExited(MouseEvent e){
				isEntered=false;
			}
			public void mousePressed(MouseEvent e){

			}
			public void mouseReleased(MouseEvent e){

			}
		});
	}
}