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

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;
import main.GConstants.EFileMenuItem;
import shapeTools.GShapeTool;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	// components

	// associations
	private GPanel panel;

	public GFileMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem efileMenuItem: EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(efileMenuItem.getText());
			menuItem.setActionCommand(efileMenuItem.name());//eNew--> 이게 스트링으로 변함(베리어블 주소로 가리키고 있지만 스트링으로 변함)
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}



	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	@SuppressWarnings("unchecked")
	private void openFile() {
		File file = new File("test");
		try {
			ObjectInputStream objectInputStream = new  ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(file)));
			Vector<GShapeTool> shapes = 
					(Vector<GShapeTool>) objectInputStream.readObject();
			this.panel.setShapes(shapes);
			objectInputStream.close();
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}

	}

	private void saveFile() {
		File file = new File("test");
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(file)));
			objectOutputStream.writeObject(this.panel.getShapes());
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EFileMenuItem eMenuItem = EFileMenuItem.valueOf(e.getActionCommand()); //메뉴 아이템을 찾아옴
			if (eMenuItem == EFileMenuItem.eOpen) {
				openFile();
			} else if (eMenuItem == EFileMenuItem.eSave) {
				saveFile();
			}
		}

	}

}
