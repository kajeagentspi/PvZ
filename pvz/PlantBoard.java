package pvz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;

//BETA
public class PlantBoard extends JPanel{
	private JLabel xPos=new JLabel("x: ");
	private JLabel yPos=new JLabel("y: ");
	private GamePanel game;
	private String plantActive;
	private int sun;
	private JLabel sunLabel;
	public PlantBoard(GamePanel game){
		this.game=game;
		this.setPreferredSize(new Dimension(1000, 100));
		this.setBackground(new Color(255,205,148));
		this.setOpaque(true);
		this.setLocation(0,0);
		this.sunLabel=new JLabel();
		JButton plause=new JButton("Pause");
		JButton gotoMenu =new JButton("Go To Main Menu");
		//update
		JButton peashootercard=new JButton();
		JButton cherrybombcard=new JButton();
		JButton potatominecard=new JButton();
		JButton repeatercard=new JButton();
		JButton wallnutcard=new JButton();

		this.loadIcon(peashootercard,"sprites/cardbuttons/PeaShooter.png");
		this.loadIcon(cherrybombcard,"sprites/cardbuttons/CherryBomb.png");
		this.loadIcon(potatominecard,"sprites/cardbuttons/PotatoMine.png");
		this.loadIcon(wallnutcard,"sprites/cardbuttons/WallNut.png");
		this.loadIcon(repeatercard,"sprites/cardbuttons/Repeater.png");

		this.add(sunLabel);
		this.add(plause);
		this.add(gotoMenu);
		
		this.add(peashootercard);
		this.add(cherrybombcard);
		this.add(wallnutcard);
		this.add(potatominecard);
		this.add(repeatercard);

		this.add(xPos);
		this.add(yPos);

		gotoMenu.addActionListener(new GoToMenuListener(game));
		peashootercard.addActionListener(new CardButtonListener(this,"PeaShooter"));
		cherrybombcard.addActionListener(new CardButtonListener(this,"CherryBomb"));
		potatominecard.addActionListener(new CardButtonListener(this,"PotatoMine"));
		wallnutcard.addActionListener(new CardButtonListener(this,"WallNut"));
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
	}
	public void loadCards(){

	}
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
	public void loadIcon(JButton button,String cardLocation){
		try {
    		Image img = Toolkit.getDefaultToolkit().getImage(new URL("file:"+cardLocation));
    		button.setIcon(new ImageIcon(img));
  		}catch(Exception e){}
  		button.setBorderPainted(false);
  		button.setContentAreaFilled(false);
	}
	public void addSun(int count){
		this.sun+=count;
		this.sunLabel.setText(Integer.toString(this.sun));
		System.out.println(this.sun);
	}
	
}