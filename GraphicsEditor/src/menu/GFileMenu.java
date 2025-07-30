package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	// components
	private JMenuItem openItem;
	private JMenuItem saveItem;

	// associations
	private GPanel panel;

	public GFileMenu() {
		super("File");
		ActionHandler actionHandler = new ActionHandler();

		this.openItem = new JMenuItem("open");
		this.openItem.setActionCommand("open");
		this.openItem.addActionListener(actionHandler);
		this.add(this.openItem);

		this.saveItem = new JMenuItem("save");
		this.saveItem.setActionCommand("save");
		this.addActionListener(actionHandler);
		this.add(this.saveItem);

	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	private void openFile() {

	}

	private void saveFile() {

	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "open") {
				openFile();
			} else if (e.getActionCommand() == "save") {
				saveFile();
			}
		}

	}

}
