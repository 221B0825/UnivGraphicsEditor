package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import main.GConstants.CDrawingState;
import main.GConstants.CGToolBar;

public class GPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private String selection;
	private String drawingState;
	
	private GPolygon polygon;

	private GMouseHandler mouseHandler;

	public GPanel() {

		this.selection = CGToolBar.rectButton; // 초기값 설정
		this.drawingState = CDrawingState.stop; // 처음 실행시 그리지 않고 있음
		
		this.polygon = new GPolygon();
		
		this.mouseHandler = new GMouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		
	}

	public void paint(Graphics graphics) {

	}
	
	public void setSelection(String selection) {
		
		this.selection = selection;
	}
	
	public void setState(String state) {
		this.drawingState = state;
	}
	
	
	public void initPoint(int x, int y) {
		
		this.polygon.initPoint(x,y);
		
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
		
	}
	public void keepDrawing(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setXORMode(getBackground());
		
		this.polygon.draw(graphics2D);		
		this.polygon.setPoint(x,y);
		this.polygon.draw(graphics2D);
		
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
			if (selection.equals(CGToolBar.rectButton)) {
				graphics2D.drawRect(x0, y0, x1 - x0, y1 - y0);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawRect(x0, y0, x1 - x0, y1 - y0);
			} else if (selection.equals(CGToolBar.ovalButton)) {
				graphics2D.drawOval(x0, y0, x1 - x0, y1 - y0);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawOval(x0, y0, x1 - x0, y1 - y0);
			} else if(selection.equals(CGToolBar.lineButton)) {
				graphics2D.drawLine(x0, y0, x1, y1);
				// new paint
				x1 = e.getX();
				y1 = e.getY();
				graphics2D.drawLine(x0, y0, x1, y1);
			} 
				
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			
			if(selection.equals(CGToolBar.polygonButton)&& drawingState.equals(CDrawingState.drawing)) {
				keepDrawing(x,y);
			}
	
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			if(event.getClickCount() == 1) { 
				this.mouseOneClicked(event);
			}else if(event.getClickCount()==2) {
				this.mouseTwoClicked(event);
			}
		}

		// start drawing
		private void mouseOneClicked(MouseEvent event) {
			if(selection.equals(CGToolBar.polygonButton)&& drawingState.equals(CDrawingState.stop)) {
				
				int x = event.getX();
				int y = event.getY();
				
				initPoint(x,y);
				drawingState = CDrawingState.drawing;
				
			}
			if(selection.equals(CGToolBar.polygonButton)&& drawingState.equals(CDrawingState.drawing)) {
				int x = event.getX();
				int y = event.getY();
				addPoint(x, y);
			}
			
			
		}
		
		// finish drawing
		private void mouseTwoClicked(MouseEvent event) {
			if(selection.equals(CGToolBar.polygonButton)&& drawingState.equals(CDrawingState.drawing)) {
				
				int x=event.getX();
				int y=event.getY();
				
				drawingState = CDrawingState.stop;
			}
			
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
