package pvz;
import java.net.*;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.ImageIcon;
import javax.sound.sampled.*;
import java.io.Serializable;



public class Sprite extends JPanel implements Serializable{
	protected final static int DAMAGE_NORMAL=1;
	protected final static int DAMAGE_HEAVY=90;
	protected final static int TOUGHNESS_NORMAL=5;
	protected final static int TOUGHNESS_HIGH=80;
	protected final static int TOUGHNESS_INFINITE=255;
	protected int xPos, yPos,height,width;
	protected Rectangle rectangle;
	protected boolean suspendFlag;
	protected String location;
	protected Image image;
	public Sprite(int xPos, int yPos, int width, int height, String location){
		this.setSize(1000,500);
		this.xPos = xPos;
		this.yPos = yPos;
		this.height=height;
		this.width=width;
		this.location=location;
		this.image=this.loadImage(location);
		this.rectangle=new Rectangle(xPos,yPos,width,height);
		this.setOpaque(false);
	}

	private Image loadImage(String location){
		try{
			return Toolkit.getDefaultToolkit().getImage(new URL("file:"+location));
		} catch(Exception e){}	
		return null;
	}
	public void changeIcon(String location){
		this.location=location;
		try{
			this.image=Toolkit.getDefaultToolkit().getImage(new URL("file:"+location));
		}catch(Exception e){}
	}
	public Image getImage(){
		return this.image;
	}

	public int getXPos(){
		return this.xPos;
	}

	public int getYPos(){
		return this.yPos;
	}
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	protected void updateRectangle(){
		this.rectangle=new Rectangle(this.xPos,this.yPos,width,height);
	}

	
	public synchronized void pause(){
		this.suspendFlag = true;
	}
	public synchronized void resume(){
		this.suspendFlag = false;
		notify();
	}
	public boolean getSuspendFlag(){
		return this.suspendFlag;
	}
	public void soundComponent(Clip clip) {
        try {
            if(clip.isRunning()){
            	clip.stop();
			}
			clip.setFramePosition(0);
        	clip.start();
        }   catch(Exception e) {
        	System.out.println(e);
        }

    }

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.getImage(),this.getXPos(),this.getYPos(),null);
		Toolkit.getDefaultToolkit().sync();
	}
}