package main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GPanel extends JPanel {
	private MouseHandler mouseHandler;

	private String tool;
	private int x1;
	private int y1;
	private int w;
	private int h;

	public GPanel() {
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.tool = null;

	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public void draw(int x, int y, int width, int height) {
		
		Graphics g = getGraphics();
		g.setXORMode(getBackground());

		g.drawRect(x, y, width, height);

	}

	class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			draw(x1, y1, w, h);

			int x2 = e.getX();
			int y2 = e.getY();

			w = x2 - x1;
			h = y2 - y1;

			draw(x1, y1, w, h);

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			
			w = 0;
			h = 0;
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
