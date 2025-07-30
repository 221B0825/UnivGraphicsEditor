package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	public MainPanel() {
		this.setBackground(Color.GREEN);
	}
	
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		
		graphics.drawRect(20, 20, 20, 20);
	}

}
