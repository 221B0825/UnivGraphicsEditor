package frame;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import main.GConstants;

public class GFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private GPanel panel;
	private GToolBar toolBar;
	//constructor
	public GFrame() {
		// initialize attributes
		this.setLocation(GConstants.CFrame.point);
		this.setSize(GConstants.CFrame.dimesion);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(GConstants.CFrame.title);
		
		// initialize components
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		
		//set association
		this.toolBar.setAssciation(this.panel);
	
	}
	public void initialize() {
		this.toolBar.initialize();
		this.panel.initialize();
		
	}

}
