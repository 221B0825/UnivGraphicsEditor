package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import main.GConstants.CGToolBar;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private JButton rectButton;
	private JButton ovalButton;
	private JButton lineButton;
	private JButton polygonButton;
	
	// association
	private GPanel panel;
	
	
	public GToolBar() {
		// initialize components
		ActionHandler actionHandler = new ActionHandler();
		
		this.rectButton = new JButton(CGToolBar.rectButton);
		this.rectButton.addActionListener(actionHandler);
		this.add(this.rectButton);
		
		this.ovalButton = new JButton(CGToolBar.ovalButton);
		this.ovalButton.addActionListener(actionHandler);
		this.add(this.ovalButton);
		
		this.lineButton = new JButton(CGToolBar.lineButton);
		this.lineButton.addActionListener(actionHandler);
		this.add(this.lineButton);
		
		this.polygonButton = new JButton(CGToolBar.polygonButton);
		this.polygonButton.addActionListener(actionHandler);
		this.add(this.polygonButton);
		
	}
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(rectButton)) {
				panel.setSelection(CGToolBar.rectButton);
			
			}else if(event.getSource().equals(ovalButton)) {
				panel.setSelection(CGToolBar.ovalButton);
			
			}else if(event.getSource().equals(lineButton)) {
				panel.setSelection(CGToolBar.lineButton);
			
			}else if(event.getSource().equals(polygonButton)) {
				panel.setSelection(CGToolBar.polygonButton);
				
			}
		}
		
	}
	public void setAssciation(GPanel panel) {
		this.panel = panel;
		
	}
}
