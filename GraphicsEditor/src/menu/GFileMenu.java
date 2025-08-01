package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	
	//working variable
	private File currentFile; //현재 작업하고 있는 파일

	public GFileMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem efileMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(efileMenuItem.getText());
			menuItem.setActionCommand(efileMenuItem.name());// eNew--> 이게 스트링으로 변함(베리어블 주소로 가리키고 있지만 스트링으로 변함)
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		this.currentFile = null;
	
	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	@SuppressWarnings("unchecked")
	private void openFile(File file) {
		//File file = new File("test");
		this.currentFile = file;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(currentFile)));
			Vector<GShapeTool> shapes = (Vector<GShapeTool>) objectInputStream.readObject();
			this.panel.setShapes(shapes);
			objectInputStream.close();

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	private void saveFile(File file) {
		//File file = new File("test");
		this.currentFile = file;
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(currentFile)));
			objectOutputStream.writeObject(this.panel.getShapes());
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void nnew() {
		if (this.panel.isModified()) {
			// save 현재 사용하고 있는 패널에서 변경사항이 있을 경우
			int reply = JOptionPane.showConfirmDialog(this.panel, "변경내용을 저장할까요?");
			if (reply == JOptionPane.OK_OPTION) { //저장하겠다고 했을 때
				if(this.currentFile == null) { //저장한적이 없으면
					this.saveAs();
					this.panel.clearScreen();
					this.currentFile = null;
					this.panel.setModified(false);
				}else { //저장된 파일이면
					this.save();
					this.panel.clearScreen();
					this.currentFile = null;
					this.panel.setModified(false);
					
				}
			} else if (reply == JOptionPane.NO_OPTION) { //저장하지 않겠다고 했을 때
				this.panel.clearScreen();
				this.currentFile = null;
				this.panel.setModified(false);
			} else if (reply == JOptionPane.CANCEL_OPTION) { //취소했을 때

			}
		}else { //파일을 열거나 저장 후, 새 파일을 만들 때 변경사항이 없을 경우
			this.panel.clearScreen();
			this.currentFile = null;
			this.panel.setModified(false);
		}

	}

	private void open() {
		if (this.panel.isModified()) {
			// 현재 보고있는 파일에서 변경사항이 있을 때
			if(this.currentFile != null) { //저장되어 있는 파일이면
				this.save();
			}else { //저장되어 있지 않은 파일이면
				this.saveAs();
			}
		}
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(this.panel);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentFile = chooser.getSelectedFile();
			this.openFile(this.currentFile);
			this.panel.setModified(false);	
		}
		
	}

	private void save() {
		if (this.panel.isModified()) {
			// save 저장한 적이 있으면(파일 명이 존재하면) 그 파일에 그냥 저장하면 되고
			if(this.currentFile != null) {
				this.saveFile(this.currentFile);
			}else {//저장한 적이 없으면 어디에 저장할 것인지 띄우고 이름 넣고 저장해야 함
				this.saveAs();
			}
			this.panel.setModified(false);
		}

	}

	private void saveAs() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(this.panel);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//새로 파일을 만듦
			File file = chooser.getSelectedFile();
			this.currentFile = file;
			this.saveFile(file);
			this.panel.setModified(false);
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
				break;
			default:
				break;

			}
		}

	}

}
