
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private JButton rectButton;
	private JButton ovalButton;
	private JButton lineButton;
	
	private GPanel panel;
	
	
	public GToolBar() {
		this.panel = new GPanel();
		ActionHandler actionHandler = new ActionHandler();
		
		this.rectButton = new JButton("Rect");
		this.rectButton.addActionListener(actionHandler);
		this.add(this.rectButton);
		
		this.ovalButton = new JButton("Oval");
		this.ovalButton.addActionListener(actionHandler);
		this.add(this.ovalButton);
		
		this.lineButton = new JButton("line");
		this.lineButton.addActionListener(actionHandler);
		this.add(this.lineButton);
	}
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource().equals(rectButton)) {
				panel.draw("rectButton");
				//System.out.println("rectButton");
			}else if(event.getSource().equals(ovalButton)) {
				panel.draw("ovalButton");
				//System.out.println("ovalButton");
			}else if(event.getSource().equals(lineButton)) {
				panel.draw("lineButton");
			}
		}
		
	}
	public void setAssciation(GPanel panel) {
		this.panel = panel;
		
	}
}
