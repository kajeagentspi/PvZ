/*************************************************************************************************************************
 *
 * CMSC 22 2nd Semester SY 2016-1017
 * Multithreading Example (with GUI): Cart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;

/**********************************************************************************************
* Sprite class which is a JPanel. 
* Contains attributes and methods to render an object as a JPanel with image.
***********************************************************************************************/
public class Sprite extends JPanel {
	protected Image img;
	protected int xPos, yPos,height,width;
	protected Rectangle rectangle;
	protected boolean suspendFlag;

	/**********************************************************************************************
	* Sprite constructor.
	* Initializes the attributes and loads the image for this sprite.
	***********************************************************************************************/
	public Sprite(int xPos, int yPos, int width, int height, String filename){
		this.setSize(900,500);
		this.xPos = xPos;
		this.yPos = yPos;
		this.height=height;
		this.width=width;
		this.loadImage(filename);
		this.rectangle=new Rectangle(xPos,yPos,width,height);
		this.setOpaque(false);
		this.suspendFlag=true;
	}

	private void loadImage(String filename){
		try{
			img = Toolkit.getDefaultToolkit().getImage(filename);
		} catch(Exception e){}	
	}

	public Image getImage(){
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

	/**********************************************************************************************
	* Overrides the paintComponent method of JPanel.
	* Draws the image on its x and y coordinates.
	***********************************************************************************************/
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.getImage(),this.getXPos(),this.getYPos(),null);
		Toolkit.getDefaultToolkit().sync();
	}
}