package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private GToolbar toolbar;
	private GPanel panel;
	
	//constructor
	public GFrame() {
		// initialize attributes
		this.setLocation(200,100);
		this.setSize(400,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		// initialize components
		this.toolbar = new GToolbar();
		this.add(this.toolbar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void initialize() {
		this.toolbar.initialize(this.panel);
	}

}
