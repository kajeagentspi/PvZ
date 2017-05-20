package pvz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

//BETA
public class PlantBoard extends JPanel implements Serializable{
	JLabel xPos=new JLabel("x: ");
	JLabel yPos=new JLabel("y: ");
	GamePanel game;
	public PlantBoard(GamePanel game){
		this.game=game;
		this.setPreferredSize(new Dimension(1000, 100));
		this.setBackground(new Color(255,205,148));
		this.setOpaque(true);
		this.setLocation(0,0);
		JButton plause=new JButton("Pause");
		JButton gotoMenu =new JButton("Go To Main Menu");
		this.add(plause);
		this.add(gotoMenu);
		this.add(xPos);
		this.add(yPos);
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
		gotoMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!game.getStage().getStatus()){
					game.getStage().setStatus();
					game.getStage().pause();
				}game.getStage().setStatus();
				game.getMenu().saveGame();
				game.getMenu().showMenu();
			}
		});	
	}
		//temporary code
	public void showCoords(int mxPos,int myPos){
		this.xPos.setText("x: "+mxPos);
		this.yPos.setText("y: "+myPos);
	}
	//end temporary code
}