package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import main.GConstants.EShapeTool;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	// association
	private GPanel panel;
	
	
	public GToolBar() {
		// initialize components
		ActionHandler actionHandler = new ActionHandler();
		
		for(EShapeTool eButton: EShapeTool.values()) {
			JButton button = new JButton(eButton.getText());
			button.setActionCommand(eButton.toString()); // eRectangle을 반환
			button.addActionListener(actionHandler);
			this.add(button);
		}
		
	}
	public void initialize() { //연결된 속성들 가지고 
		((JButton)(this.getComponent(EShapeTool.eRectangle.ordinal()))).doClick();
		
	}
	public void setAssciation(GPanel panel) {
		this.panel = panel;
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
				EShapeTool eShapeTool = EShapeTool.valueOf(event.getActionCommand());
				panel.setSelection(eShapeTool.getShapeTool());
			}
		}

	
		
	}
	

