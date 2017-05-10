/*************************************************************************************************************************
 *
 * CMSC 22 2nd Semester SY 2016-1017
 * Multithreading Example (with GUI): Cart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


/**********************************************************************************************
* MyFrame class which is a JFrame. 
* The window that contains the Stage object (where the Cart instances will race).
***********************************************************************************************/
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
				if(stage.getStatus()){
					stage.setStatus();
					stage.pause();
					plause.setText("Resume");
				}else{
					stage.setStatus();
					stage.resume();
					plause.setText("Pause");
				}
			}
		});
		plause.setBounds(50,100,95,30);
		//END
		gbc.gridy++;
      	gbc.anchor = GridBagConstraints.LAST_LINE_START;
		container.add(stage, gbc);
      	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		container.add(pboard,gbc);
		gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		container.add(plause,gbc);
		

		// container.setBackground(Color.GREEN);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}	
}
