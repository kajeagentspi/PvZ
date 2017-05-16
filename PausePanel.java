import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//BETA
public class PausePanel extends JPanel {
	public PausePanel(Stage stage){
		this.setPreferredSize(new Dimension(900, 100));
	  	this.setBackground(Color.RED);
	  	this.setOpaque(true);
	  	this.setLocation(500,0);
	  	JButton plause=new JButton("Pause");
	}
}