import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.sound.sampled.*;


public class Sprite extends JPanel {
	protected final static int DAMAGE_NORMAL=1;
	protected final static int DAMAGE_HEAVY=90;
	protected final static int TOUGHNESS_NORMAL=5;
	protected final static int TOUGHNESS_HIGH=80;
	protected final static int TOUGHNESS_INFINITE=255;

	protected Image  img;
	protected int xPos, yPos,height,width;
	protected Rectangle rectangle;
	protected boolean suspendFlag;

	public Sprite(int xPos, int yPos, int width, int height, String filename){
		this.setSize(900,500);
		this.xPos = xPos;
		this.yPos = yPos;
		this.height=height;
		this.width=width;
		this.loadImage(filename);
		this.rectangle=new Rectangle(xPos,yPos,width,height);
		this.setOpaque(false);
	}

	private void loadImage(String filename){
		try{
			img = Toolkit.getDefaultToolkit().getImage(filename);
			
			// img = ImageIO.read(new File(filename));
		} catch(Exception e){}	
	}

	public Image  getImage(){
		return this.img;
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

	public void changeIcon(String icon){
		this.loadImage(icon);
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