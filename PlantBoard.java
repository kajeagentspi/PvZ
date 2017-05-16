import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//BETA
public class PlantBoard extends JPanel{
	public PlantBoard(Stage stage){
		this.setPreferredSize(new Dimension(900, 100));
	  	this.setBackground(new Color(255,205,148));
	  	this.setOpaque(true);
	  	this.setLocation(0,0);
	  	JButton plause=new JButton("Pause");
	  	// this.add(plause);
	}
}