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
import main.GConstants.EShapeTool;

public class GPanel extends JPanel {
	////////////////////////////////////////////////////
	// attributes
	private static final long serialVersionUID = 1L;

	// components
	private GMouseHandler mouseHandler;

	// association

	// working Objects
	private String drawingState;
	private GShapeTool shapeTool;

	////////////////////////////////////////////////////
	// getters and setters
	public void setSelection(GShapeTool shapeTool) {

		this.shapeTool = shapeTool;
	}

	// constructors
	public GPanel() {

		this.mouseHandler = new GMouseHandler();

		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.addMouseWheelListener(this.mouseHandler);

	}

	public void initialize() {
		this.drawingState = CDrawingState.stop;
	}
	// methods

	public void paint(Graphics graphics) {

	}

	private void mouseOneClicked(MouseEvent e) {
		if (shapeTool.equals(EShapeTool.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.stop)) {
			shapeTool.initPoint(e.getX(), e.getY());
			drawingState = CDrawingState.drawing;
		}
		if (shapeTool.equals(EShapeTool.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {
			shapeTool.setIntermediate(e.getX(), e.getY());
		}
	}

	private void mouseTwoClicked(MouseEvent e) {
		if (shapeTool.equals(EShapeTool.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {
			drawingState = CDrawingState.stop;
		}
	}

	private void setOrigin(int x, int y) {
		shapeTool.initCoordinate(x, y);
	}

	private void animate(int x, int y) {
		if(shapeTool.equals(EShapeTool.ePolygon.getShapeTool())){
			//폴리곤은 드래그 사용 X
		}else {
			// exclusive or mode
			Graphics2D graphics2D = (Graphics2D) getGraphics();
			graphics2D.setXORMode(getBackground());
			shapeTool.draw(graphics2D, x, y);
		}
		
	}

	// mouseMoved-polygon만 적용
	private void keepDrawing(int x, int y) {

		if (shapeTool.equals(EShapeTool.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {
			Graphics2D graphics2d = (Graphics2D) getGraphics();
			graphics2d.setXORMode(getBackground());
			// polygon은 마지막으로 찍은 점에서부터 현재 마우스의 위치까지 계속해서 움직여질 때마다 그려주어야 함
			shapeTool.keepDrawing(graphics2d, x, y);
		}

	}

	private void finishDrawing(int x, int y) {

	}

	////////////////////////////////////////////////////
	// inner classes
	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			animate(e.getX(), e.getY());

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			keepDrawing(e.getX(), e.getY());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouseOneClicked(e);
			} else if (e.getClickCount() == 2) {
				// 더블클릭하면 종료
				mouseTwoClicked(e);
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			setOrigin(e.getX(), e.getY());

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			finishDrawing(e.getX(), e.getY());
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

}
