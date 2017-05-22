package pvz;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MyFrame extends JFrame{
	public int mxPos;
	public int myPos;
	private Menu menu;
	private Container container;
	public MyFrame (){
		this.setPreferredSize(new Dimension(1005,630));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.container= this.getContentPane();
		container.setLayout(new CardLayout());
		this.setResizable(false);
		menu=new Menu(this);
		container.add(menu,"first");
		this.pack();
		this.setVisible(true);
	}
	//returns container
	public Container getContainer(){
		return this.container;
	}
}