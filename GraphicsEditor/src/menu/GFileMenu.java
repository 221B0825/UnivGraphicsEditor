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
import main.GConstants.EFileMenuItem;
import shapeTools.GShapeTool;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	// components

	// associations
	private GPanel panel;
	private File file;

	public GFileMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem efileMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(efileMenuItem.getText());
			menuItem.setActionCommand(efileMenuItem.name());// eNew--> 이게 스트링으로 변함(베리어블 주소로 가리키고 있지만 스트링으로 변함)
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		this.file = null;
	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	@SuppressWarnings("unchecked")
	private void openFile() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(this.file)));
			Vector<GShapeTool> shapes = (Vector<GShapeTool>) objectInputStream.readObject();
			this.panel.setShapes(shapes);
			objectInputStream.close();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();

		}

	}

	private void saveFile() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(this.file)));
			objectOutputStream.writeObject(this.panel.getShapes());
			objectOutputStream.close();
			this.panel.setModified(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean checkSaveOrNot() {
		boolean bCancel = true;
		if (this.panel.isModified()) {
			int reply = JOptionPane.showConfirmDialog(this.panel, "변경내용을 저장할까요?");
			if (reply == JOptionPane.OK_OPTION) {
				this.save();
				bCancel = false;
			} else if (reply == JOptionPane.NO_OPTION) {
				this.panel.setModified(false);
				bCancel = false;
			}
		} else {
			bCancel = false;
		}
		return bCancel;
	}

	private void nnew() {
		if (!checkSaveOrNot()) {
			this.panel.clearScreen();
			this.file = null;
		}
	}

	private void open() {
		if (!checkSaveOrNot()) { // is not cancel
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this.panel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				this.file = chooser.getSelectedFile();
				this.openFile();
			}
		} // else {cancel}
	}

	private void save() {
		if (this.panel.isModified()) {
			if (this.file == null) {
				this.saveAs();
			} else {
				this.saveFile();
			}
		}

	}

	private void saveAs() {
		// save
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(this.panel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.file = chooser.getSelectedFile();
			this.saveFile();
		}

	}
	private void exitProgram() {
		if (!checkSaveOrNot()) {
			System.exit(0);
		}
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EFileMenuItem eMenuItem = EFileMenuItem.valueOf(e.getActionCommand()); // 메뉴 아이템을 찾아옴
			switch (eMenuItem) {
			case eNew:
				nnew();
				break;
			case eOpen:
				open();
				break;
			case eSave:
				save();
				break;
			case eSaveAs:
				saveAs();
				break;
			case ePrint:
				break;
			case eExit:
				exitProgram();
				break;
			default:
				break;

			}
		}

	}

}
