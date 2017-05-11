import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class MyFrame extends JFrame {
	
	public MyFrame (){
		
		this.setPreferredSize(new Dimension(1200,628));
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
      	GridBagConstraints gbc = new GridBagConstraints();
      	gbc.weightx = 1.0;
      	gbc.weighty = 1.0;
      	gbc.gridx = 0;
      	gbc.gridy = 0;
		Container container = this.getContentPane();	
		Stage stage = new Stage();
		PlantBoard pboard=new PlantBoard();
		//BETA
		JButton plause=new JButton("Pause");
		plause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				plause.setFocusable(false);
				if(!stage.getStatus()){
					stage.setStatus();
					stage.pause();
					plause.setText("Resume");
				}
				else if(stage.getStatus()){
					stage.setStatus();
					stage.resume();
					plause.setText("Pause");
				}
			}
		});
		plause.setBounds(50,100,95,30);//set the size of button plause
		//END
		gbc.gridy++;
      	gbc.anchor = GridBagConstraints.LAST_LINE_START;
		container.add(stage, gbc);
      	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		container.add(pboard,gbc);
		gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		container.add(plause,gbc);
		this.setFocusable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}	
}
