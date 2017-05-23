package pvz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

//BETA
public class PlantBoard extends JPanel{
	private JLabel xPos=new JLabel("x: ");
	private JLabel yPos=new JLabel("y: ");
	private GamePanel game;
	private String plantActive;
	private JLabel sunLabel;
	private JLabel scoreLabel;
	private JLabel levelLabel;
	public PlantBoard(GamePanel game){
		this.game=game;
		this.setPreferredSize(new Dimension(1000, 100));
		this.setBackground(new Color(255,205,148));
		this.setOpaque(true);
		this.setLocation(0,0);
		this.sunLabel=new JLabel("Sun: 50");
		this.scoreLabel=new JLabel("Score: 0");
		this.levelLabel=new JLabel("Level: 1");

		JButton plause=new JButton("Pause");
		JButton gotoMenu =new JButton("Go To Main Menu");
		//update
		JButton sunflowercard=new JButton();
		JButton peashootercard=new JButton();
		JButton cherrybombcard=new JButton();
		JButton potatominecard=new JButton();
		JButton repeatercard=new JButton();
		JButton wallnutcard=new JButton();
		JButton chompercard=new JButton();
		JButton shovelcard=new JButton();
		this.loadIcon(sunflowercard,"sprites/cardbuttons/SunFlower.png");
		this.loadIcon(peashootercard,"sprites/cardbuttons/PeaShooter.png");
		this.loadIcon(cherrybombcard,"sprites/cardbuttons/CherryBomb.png");
		this.loadIcon(potatominecard,"sprites/cardbuttons/PotatoMine.png");
		this.loadIcon(wallnutcard,"sprites/cardbuttons/WallNut.png");
		this.loadIcon(chompercard,"sprites/cardbuttons/Chomper.png");
		this.loadIcon(shovelcard,"sprites/cardbuttons/Shovel.png");
		
		this.add(sunflowercard);
		this.add(peashootercard);
		this.add(cherrybombcard);
		this.add(wallnutcard);
		this.add(potatominecard);
		this.add(chompercard);
		this.add(shovelcard);

		this.add(sunLabel);
		this.add(scoreLabel);
		this.add(levelLabel);

		// this.add(xPos);
		// this.add(yPos);
		this.add(plause);
		this.add(gotoMenu);

		gotoMenu.addActionListener(new GoToMenuListener(game));
		sunflowercard.addActionListener(new CardButtonListener(this,"SunFlower"));
		peashootercard.addActionListener(new CardButtonListener(this,"PeaShooter"));
		cherrybombcard.addActionListener(new CardButtonListener(this,"CherryBomb"));
		potatominecard.addActionListener(new CardButtonListener(this,"PotatoMine"));
		wallnutcard.addActionListener(new CardButtonListener(this,"WallNut"));
		chompercard.addActionListener(new CardButtonListener(this,"Chomper"));
		shovelcard.addActionListener(new CardButtonListener(this,"Shovel"));
		plause.addActionListener(new PauseListener(game));	
	}

	public void setplantActive(String plantActive){
		this.plantActive=plantActive;
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
  		button.setPreferredSize(new Dimension(50,70));
	}
	public void setSun(int sun){
		this.sunLabel.setText("Sun: "+Integer.toString(sun));
		System.out.println(sun);
	}
	public void setScore(int score){
		this.scoreLabel.setText("Score: "+Integer.toString(score));
		System.out.println(score);
	}
	public void setLevel(int level){
		this.levelLabel.setText("Level: "+Integer.toString(level));
		System.out.println(level);
	}
}