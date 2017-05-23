package pvz;
import java.awt.event.*;
public class CardButtonListener implements ActionListener{
	private PlantBoard pboard;
	private String plantActive;
	public CardButtonListener(PlantBoard pboard,String plantActive){
		this.pboard=pboard;
		this.plantActive=plantActive;
	}
	public void actionPerformed(ActionEvent e){
		this.pboard.setPlantActive(this.plantActive);
	}
}