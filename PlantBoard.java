import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//BETA
public class PlantBoard extends JPanel{
	JLabel xPos=new JLabel("x: ");
	JLabel yPos=new JLabel("y: ");
	Stage stage;
	public PlantBoard(Stage stage){
		this.stage=stage;
		this.setPreferredSize(new Dimension(900, 75));
		this.setBackground(new Color(255,205,148));
		this.setOpaque(true);
		this.setLocation(0,0);
		JButton plause=new JButton("Pause");
		this.add(plause);
		this.add(xPos);
		this.add(yPos);
		plause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!stage.getStatus()){
						stage.setStatus();
						stage.pause();
					}
					else if(stage.getStatus()){
						stage.setStatus();
						stage.resume();
					}
			}
		});
	}
	//temporary code
	public void showCoords(int mxPos,int myPos){
		xPos.setText("x: "+mxPos);
		yPos.setText("y: "+myPos);
	}
	//end temporary code
}