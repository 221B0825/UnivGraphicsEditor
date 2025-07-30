package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private MainPanel mainPanel;
	
	public MainFrame() {
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.mainPanel = new MainPanel();
		this.add(mainPanel,BorderLayout.CENTER);
	}
	
	
}
