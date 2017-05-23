package pvz;
import java.awt.event.*;
public class PlanterListener implements MouseListener{
	private String plantActive;
	private Stage stage;
	private int mxPos;
	private int myPos;
	private GamePanel game;
	private final static int SUNFLOWER_COST=50;
	private final static int PEASHOOTER_COST=100;
	private final static int CHERRYBOMB_COST=150;
	private final static int POTATOMINE_COST=25;
	private final static int CHOMPER_COST=150;
	private final static int WALLNUT_COST=50;
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
		int cost=0;
		this.plantActive=game.getPboard().getPlantActive();
		row-=1;
		if(row>=0&&col>0&&this.plantActive!=null){
			Plant plant=null;
			switch(this.plantActive){
				case "SunFlower":
					if(game.getSun()>=SUNFLOWER_COST){
						plant=new SunFlower(col*100,row*100,this.stage);
						cost=SUNFLOWER_COST;
					}
					break;
				case "PeaShooter":
					if(game.getSun()>=PEASHOOTER_COST){
						plant=new PeaShooter(col*100,row*100,this.stage);
						cost=PEASHOOTER_COST;
					}
					break;
				case "CherryBomb":
					if(game.getSun()>=CHERRYBOMB_COST){
						plant=new CherryBomb(col*100,row*100,this.stage);
						cost=CHERRYBOMB_COST;
					}
					break;
				case "PotatoMine":
					if(game.getSun()>=POTATOMINE_COST){
						plant=new PotatoMine(col*100,row*100,this.stage);
						cost=POTATOMINE_COST;
					}
					break;
				case "Chomper":
					if(game.getSun()>=CHOMPER_COST){
						plant=new Chomper(col*100,row*100,this.stage);
						cost=CHOMPER_COST;
					}
					break;
				case "WallNut":
					if(game.getSun()>=WALLNUT_COST){
						plant=new WallNut(col*100,row*100,this.stage);
						cost=WALLNUT_COST;
					}
					break;
				case "Shovel":
					plant=new Shovel(col*100,row*100,this.stage);
					cost=0;
					break;
				default:
					plant=null;
					cost=0;
					break;
			}
			if(plant!=null){
				if(this.stage.checkSpace(plant)&&!this.stage.getStatus()){
					System.out.println("Plant added");
					this.stage.addPlant(plant);
					this.game.minusSun(cost);
					this.game.getPboard().setPlantActive(null);
				}else if(!this.stage.getStatus()&&this.plantActive=="Shovel"){
					System.out.println("Plant removed");
					this.stage.addPlant(plant);
					this.game.getPboard().setPlantActive(null);
				}else{
					System.out.println("C"+col*100);
					System.out.println("R"+row*100);
				}	
			}
		}
	}	
}