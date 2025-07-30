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
import main.GConstants.EButton;

public class GPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private String drawingState;
	private GShapeTool shapeTool;

	private GMouseHandler mouseHandler;

	public GPanel() {

		this.shapeTool = EButton.eRectangle.getShapeTool(); // 초기값 설정
		this.drawingState = CDrawingState.stop; // 처음 실행시 그리지 않고 있음

		this.mouseHandler = new GMouseHandler();

		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.addMouseWheelListener(this.mouseHandler);
	}

	public void paint(Graphics graphics) {

	}

	public void setSelection(GShapeTool shapeTool) {
		this.shapeTool = shapeTool;
		// System.out.println(shapeTool.getClass().getName());
	}

	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Graphics2D graphics2D = (Graphics2D) getGraphics();
			// exclusive or mode
			graphics2D.setXORMode(getBackground());
			if (!shapeTool.equals(EButton.ePolygon.getShapeTool())) { // polygon에서는 dragged 사용 x
				shapeTool.draw(graphics2D, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			if (shapeTool.equals(EButton.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {

				Graphics2D graphics2d = (Graphics2D) getGraphics();
				graphics2d.setXORMode(getBackground());
				// polygon은 마지막으로 찍은 점에서부터 현재 마우스의 위치까지 계속해서 움직여질 때마다 그려주어야 함
				shapeTool.keepDrawing(graphics2d, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				this.mouseOneClicked(e);
			} else if (e.getClickCount() == 2) {
				this.mouseTwoClicked(e);
			}

		}

		private void mouseOneClicked(MouseEvent e) {
			if (shapeTool.equals(EButton.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.stop)) {
				// 처음 polygon의 점 클릭
				System.out.println("first mouseClicked");
				shapeTool.initPoint(e.getX(), e.getY());
				drawingState = CDrawingState.drawing;

			}

			if (shapeTool.equals(EButton.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {
				System.out.println("mouseClicked");
				// n번째 polygon의 점 클릭
				shapeTool.addPoint(e.getX(), e.getY());
			}
		}

		private void mouseTwoClicked(MouseEvent e) {
			if (shapeTool.equals(EButton.ePolygon.getShapeTool()) && drawingState.equals(CDrawingState.drawing)) {
				// 더블클릭으로 드로잉 끝내기
				System.out.println("STOP");
				drawingState = CDrawingState.stop;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed");
			shapeTool.initCoordinate(e.getX(), e.getY());
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
