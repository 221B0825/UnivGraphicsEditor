package menu;

import javax.swing.JMenuBar;

import frame.GPanel;
import main.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private GFileMenu fileMenu;
	public GMenuBar() {
		this.fileMenu = new GFileMenu(EMenu.eFile.getText());
		this.add(this.fileMenu);
		
	}
	public void setAssciation(GPanel panel) {
		this.fileMenu.setAssciation(panel);
		
	}


}
