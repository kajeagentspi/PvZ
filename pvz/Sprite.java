package pvz;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Sprite extends JPanel{
	protected final static int DAMAGE_NORMAL=1;
	protected final static int DAMAGE_HEAVY=90;
	protected final static int DAMAGE_HIGH=90;
	protected final static int TOUGHNESS_NORMAL=5;
	protected final static int TOUGHNESS_HIGH=80;
	protected final static int TOUGHNESS_INFINITE=255;
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;
	protected boolean suspendFlag;
	protected long cliptime;
	protected AudioInputStream ais;
	protected Clip clip;
	protected Image image;
	protected Rectangle rectangle;
	protected String imageLocation;
	protected String audioLocation;
	protected URL url;

	public Sprite(int xPos, int yPos, int width, int height, String imageLocation, String audioLocation){
		this.setSize(1000,500);
		this.xPos = xPos;
		this.yPos = yPos;
		this.width=width;
		this.height=height;
		this.loadSound(audioLocation);
		this.image=this.loadImage(imageLocation);
		this.rectangle=new Rectangle(xPos,yPos,width,height);
		this.imageLocation=imageLocation;
		this.audioLocation=audioLocation;
		this.setOpaque(false);
	}
	public int getXPos(){
		return this.xPos;
	}
	public int getYPos(){
		return this.yPos;
	}
	public boolean getSuspendFlag(){
		return this.suspendFlag;
	}
	public Image getImage(){
		return this.image;
	}
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	//Loads the soundclip
	private void loadSound(String audioLocation){
		try {
			this.url = new URL("file:"+audioLocation);
			this.clip = AudioSystem.getClip();
			this.ais = AudioSystem.getAudioInputStream(this.url);
			this.clip.open(this.ais);
		} catch (Exception e) {}
	}
	//loads the image from file
	private Image loadImage(String imageLocation){
		try{
			return Toolkit.getDefaultToolkit().getImage(new URL("file:"+imageLocation));
		} catch(Exception e){}	
		return null;
	}
	//changes the image
	public void changeIcon(String imageLocation){
		this.imageLocation=imageLocation;
		this.loadImage(this.imageLocation);
	}
	//updates the rectangle
	protected void updateRectangle(){
		this.rectangle=new Rectangle(this.xPos,this.yPos,width,height);
	}
	//controls for pause/resume
	public synchronized void pause(){
		this.suspendFlag = true;
	}
	public synchronized void resume(){
		this.suspendFlag = false;
		notify();
	}
	//plays the audioclip
	public void soundComponent(Clip clip) {
		System.out.println("play");
		try {
			if(clip.isRunning()){
				clip.stop();
				this.cliptime=0;
			}
			this.clip.setMicrosecondPosition(this.cliptime);
			this.clip.start();
		}catch(Exception e) {}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.getImage(),this.getXPos(),this.getYPos(),null);
		Toolkit.getDefaultToolkit().sync();
	}
}