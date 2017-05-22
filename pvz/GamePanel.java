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
		this.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int row=myPos/100;
				int col=mxPos/100;
				row-=1;
				if(row>=0){
					Plant plant=new CherryBomb(col*100,row*100,stage);
					if(stage.checkSpace(plant)&&!stage.getStatus()){
						System.out.println("Plant added");
						stage.addPlant(plant);
					}else{
						System.out.println("C"+col*100);
						System.out.println("R"+row*100);
					}
				}
			}	
		});
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
}