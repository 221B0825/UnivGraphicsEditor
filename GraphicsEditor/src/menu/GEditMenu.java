package menu;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;
import main.GConstants.EEditMenuItem;
import shapeTools.GShapeTool;


public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private GPanel panel;
	private GShapeTool selectedShape;
	private Vector<AffineTransform> affineTransfomers;
	
	public GEditMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EEditMenuItem eEditMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eEditMenuItem.getText());
			menuItem.setActionCommand(eEditMenuItem.name());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		
		this.selectedShape = null;
		this.affineTransfomers = new Vector<AffineTransform>();
	}

	public void setAssciation(GPanel panel) {
		this.panel = panel;
	}
	private void undo() {
		this.selectedShape = this.panel.getSelected();
		this.affineTransfomers = this.selectedShape.getTransformedShape();
		
		AffineTransform affineTransform = this.selectedShape.getAffineTransform();
		affineTransform = this.affineTransfomers.get(0);
		
		this.selectedShape.setAffineTransform(affineTransform);
		
//		Graphics2D graphics2d = (Graphics2D) this.panel.getGraphics();
//		this.selectedShape.draw(graphics2d);
	}
	private void redo() {
		// TODO Auto-generated method stub
		
	}
	
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			EEditMenuItem editMenuItem = EEditMenuItem.valueOf(e.getActionCommand());
			switch (editMenuItem) {
			case eUndo:
				undo();
				break;
			case eRedo:
				redo();
				break;
			default:
				break;
			}
		
		}

	}

}
