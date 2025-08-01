package menu;

import javax.swing.JMenuBar;

import frame.GPanel;
import main.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu(EMenu.eFile.getText());
		this.add(this.fileMenu);
		
		this.editMenu = new GEditMenu(EMenu.eEdit.getText());
		this.add(this.editMenu);
		
	}
	public void setAssciation(GPanel panel) {
		this.fileMenu.setAssciation(panel);
		this.editMenu.setAssciation(panel);
	}


}
