package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class GToolbar extends JToolBar {

	private ActionHandler actionHandler;
	
	private JButton rectangleButton;
	private GPanel panel;
	
	public GToolbar() {
		this.actionHandler = new ActionHandler();
		
		this.rectangleButton = new JButton("rectangle");
		this.rectangleButton.addActionListener(actionHandler);
		this.add(this.rectangleButton);
	}
	
	public void initialize(GPanel panel) {
		this.panel = panel;
	}
	
	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panel.setTool("rectangle");
		}
	}
}

