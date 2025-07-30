package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	
	File file;

	public GFileMenu() {
		super("File");
		ActionHandler actionHandler = new ActionHandler();

		this.openItem = new JMenuItem("open");
		this.openItem.setActionCommand("open");
		this.openItem.addActionListener(actionHandler);
		this.add(this.openItem);

		this.saveItem = new JMenuItem("save");
		this.saveItem.setActionCommand("save");
		this.saveItem.addActionListener(actionHandler);
		this.add(this.saveItem);
		
		this.file = null;
	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}

	private void openFile() {
		
		this.panel.clear();
		try {
			FileInputStream fileInputStream = new FileInputStream(this.file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object object = objectInputStream.readObject();
			objectInputStream.close();
			this.panel.read(object);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	private void saveFile() {
		File file = new File("test");
		this.file = file;
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(file)));
			//System.out.println(this.panel.getShapes());
			objectOutputStream.writeObject(this.panel.getShapes());
			objectOutputStream.close();
			this.panel.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "open") {
				openFile();
			} else if (e.getActionCommand()== "save" ) {
				saveFile();
			}
		}

	}

}
