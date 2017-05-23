package pvz;
import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

public class SunCreator implements ActionListener{
	private Stage stage;
	private Timer timer;
	private Random r=new Random();

	public SunCreator(Stage stage){
		this.stage=stage;
		this.timer=new Timer(10000,this);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.timer){
			stage.addSun(new Sun(r.nextInt(900),r.nextInt(400),stage));
		}
	}
	public Timer getTimer(){
		return this.timer;
	}

}