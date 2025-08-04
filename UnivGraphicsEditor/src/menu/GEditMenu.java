package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import frame.GPanel;
import main.GConstants.EEditMenuItem;
import main.GConstants.EFileMenuItem;
import shapeTools.GShapeTool;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	// associations
	private GPanel panel;
	
	public GEditMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EEditMenuItem eEditMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eEditMenuItem.getText());
			menuItem.setActionCommand(eEditMenuItem.name());// eNew--> 이게 스트링으로 변함(베리어블 주소로 가리키고 있지만 스트링으로 변함)
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	private void redo() {
		this.panel.redo();
	}

	private void undo() {
		this.panel.undo();
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EEditMenuItem eEditItem = EEditMenuItem.valueOf(e.getActionCommand()); // 메뉴 아이템을 찾아옴
			switch (eEditItem) {
			case eUndo:
				undo();
				break;
			case eRedo:
				redo();
				break;
			case eCut:
				break;
			case eCopy:
				break;
			case ePaste:
				break;
			case eGroup:
				break;
			case eUngroup:
				break;
			default:
				break;

			}
		}

	}

}
