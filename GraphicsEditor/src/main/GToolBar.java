package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants.EButton;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	// association
	private GPanel panel;
	
	
	public GToolBar() {
		// initialize components
		ActionHandler actionHandler = new ActionHandler();
		
		// 선택한 버튼의 이미지가 달라보이게 하기 위해서는 하나만 선택되도록 묶어야 함
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(EButton eButton: EButton.values()) {
			JRadioButton button = new JRadioButton(eButton.getText());
			button.setActionCommand(Integer.toString(eButton.ordinal())); // 몇번째인지 반환해줌
			button.addActionListener(actionHandler);
			//여기에서 순차적으로 icon 삽입
			button.setIcon(eButton.getIcon());
			button.setPressedIcon(eButton.getPressedIcon());
			button.setSelectedIcon(eButton.getPressedIcon());
			buttonGroup.add(button);
			this.add(button);
		}
		//this.rectButton.doClick();
	}
	public void setAssciation(GPanel panel) {
		this.panel = panel;
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
				int i = Integer.parseInt(event.getActionCommand());
				panel.setSelection(EButton.values()[i].getShapeTool());
			}
		}
		
	}
	

