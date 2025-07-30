import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class GPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private String selectedButton;

	private GMouseHandler mouseHandler;

	public GPanel() {
		this.selectedButton = "rectangle"; // 초기값: rectangle 선택
		
		this.mouseHandler = new GMouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
	}

	public void paint(Graphics graphics) {

	}
	
	public void draw(String selected) {
		this.selectedButton = selected;
	}

	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
		private int x0, y0, x1, y1;

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Graphics2D graphics2D = (Graphics2D) getGraphics();
			// exclusive or mode
			graphics2D.setXORMode(getBackground());
			// erase
			if (selectedButton.equals("rectButton")) {
				graphics2D.drawRect(x0, y0, x1 - x0, y1 - y0);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawRect(x0, y0, x1 - x0, y1 - y0);
			} else if (selectedButton.equals("ovalButton")) {
				graphics2D.drawOval(x0, y0, x1 - x0, y1 - y0);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawOval(x0, y0, x1 - x0, y1 - y0);
			}
			else if (selectedButton.equals("lineButton")) {
				graphics2D.drawLine(x0, y0, x1, y1);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawLine(x0, y0, x1, y1);
			}
				
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

			x0 = e.getX();
			x1 = x0;

			y0 = e.getY();
			y1 = y0;

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

}
