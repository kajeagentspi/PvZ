import javax.swing.*;
import java.awt.*;
public class PlantBoard extends JPanel{
	public PlantBoard(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(900, 100));
	  	this.setBackground(new Color(255,205,148));
	  	this.setOpaque(true);
	  	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}