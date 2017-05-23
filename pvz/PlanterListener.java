package pvz;
import java.awt.event.*;
public class PlanterListener implements MouseListener{
	private String plantActive;
	private Stage stage;
	private int mxPos;
	private int myPos;
	private GamePanel game;
	public PlanterListener(GamePanel game){
		this.game=game;
		this.plantActive=game.getPboard().getPlantActive();
		this.stage=game.getStage();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		this.mxPos=game.getmxPos();
		this.myPos=game.getmyPos();
		int row=this.myPos/100;
		int col=this.mxPos/100;
		this.plantActive=game.getPboard().getPlantActive();
		row-=1;
		if(row>=0&&col>0&&this.plantActive!=null){
			Plant plant=null;
			switch(this.plantActive){
				case "PeaShooter":
					plant=new PeaShooter(col*100,row*100,this.stage);
					break;
				case "CherryBomb":
					plant=new CherryBomb(col*100,row*100,this.stage);
					break;
				case "PotatoMine":
					plant=new PotatoMine(col*100,row*100,this.stage);
					break;
				case "Repeater":
					plant=new Repeater(col*100,row*100,this.stage);
					break;
				case "WallNut":
					plant=new WallNut(col*100,row*100,this.stage);
					break;
			}
			if(plant!=null){
				if(this.stage.checkSpace(plant)&&!this.stage.getStatus()){
					System.out.println("Plant added");
					this.stage.addPlant(plant);
				}else{
					System.out.println("C"+col*100);
					System.out.println("R"+row*100);
				}	
			}
		}
	}	
}