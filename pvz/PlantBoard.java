package pvz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

//BETA
public class PlantBoard extends JPanel{
	private JLabel xPos=new JLabel("x: ");
	private JLabel yPos=new JLabel("y: ");
	private GamePanel game;
	private String plantActive;
	public PlantBoard(GamePanel game){
		this.game=game;
		this.setPreferredSize(new Dimension(1000, 100));
		this.setBackground(new Color(255,205,148));
		this.setOpaque(true);
		this.setLocation(0,0);
		JButton plause=new JButton("Pause");
		JButton gotoMenu =new JButton("Go To Main Menu");
		//update
		JButton peashootercard=new JButton("Peashooter");
		JButton cherrybombcard=new JButton("CherryBomb");
		JButton potatominecard=new JButton("PotatoMine");
		JButton repeatercard=new JButton("Repeater");
		this.add(plause);
		this.add(gotoMenu);
		this.add(peashootercard);
		this.add(cherrybombcard);
		this.add(potatominecard);
		this.add(repeatercard);
		this.add(xPos);
		this.add(yPos);
		gotoMenu.addActionListener(new GoToMenuListener(game));
		peashootercard.addActionListener(new CardButtonListener(this,"PeaShooter"));
		cherrybombcard.addActionListener(new CardButtonListener(this,"CherryBomb"));
		potatominecard.addActionListener(new CardButtonListener(this,"PotatoMine"));
		repeatercard.addActionListener(new CardButtonListener(this,"Repeater"));
		plause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!game.getStage().getStatus()){
					game.getStage().setStatus();
					game.getStage().pause();
				}
				else if(game.getStage().getStatus()){
					game.getStage().setStatus();
					game.getStage().resume();
				}
			}
		});
		// gotoMenu.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
		// 		if(!game.getStage().getStatus()){
		// 			game.getStage().setStatus();
		// 			game.getStage().pause();
		// 		}game.getStage().setStatus();
		// 		game.getMenu().saveGame();
		// 		game.getMenu().showMenu();
		// 	}
		// });	
	}
		//temporary code
	public void showCoords(int mxPos,int myPos){
		this.xPos.setText("x: "+mxPos);
		this.yPos.setText("y: "+myPos);
	}
	//end temporary code
	public synchronized void setPlantActive(String plantActive){
		this.plantActive=plantActive;
	}
	public String getPlantActive(){
		return this.plantActive;
	}
}