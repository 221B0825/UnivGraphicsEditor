package frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EDrawingState;
import shapeTools.GShapeTool;

public class GPanel extends JPanel {
	////////////////////////////////////////////////////
	// attributes

	private static final long serialVersionUID = 1L;

	// components
	private GMouseHandler mouseHandler;
	private Vector<GShapeTool> shapes;
	// association

	// working Objects

	private GShapeTool shapeTool; // 도구에 선택된 애가 있음
	private GShapeTool drawingTool; // 그걸 카피해서 그림그리는 애
	private GShapeTool selectedShape;
	private boolean isSelected; // 선택된 상황인지 아닌지 판별

	////////////////////////////////////////////////////
	// getters and setters
	public Vector<GShapeTool> getShapes() {
		return this.shapes;

	}

	public void setShapes(Vector<GShapeTool> shapes) {
		this.shapes = shapes;
		this.repaint();
	}

	public void setSelection(GShapeTool shapeTool) {
		this.shapeTool = shapeTool;

	}

	// constructors
	public GPanel() {
		this.shapes = new Vector<GShapeTool>();
		this.isSelected = false;

		this.mouseHandler = new GMouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.addMouseWheelListener(this.mouseHandler);

	}

	public void initialize() {

	}
	// methods

	public void paint(Graphics graphics) {
		for (GShapeTool shape : this.shapes) {
			shape.draw((Graphics2D) graphics);
		}
	}

	private void setInitialPoint(int x, int y) {
		this.drawingTool = this.shapeTool.newInstance();
		this.drawingTool.setInitialPoint(x, y);
	}

	private void setIntermediatePoint(int x, int y) {
		this.drawingTool.setIntermediatePoint(x, y);
	}

	private void animate(int x, int y) {
		// exclusive or mode
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setXORMode(getBackground());
		// erase
		this.drawingTool.animate(graphics2D, x, y);
	}

	private void setSelected(int x, int y) {
		Graphics2D anchors = (Graphics2D) getGraphics();
		anchors.setXORMode(getBackground());

		if (isSelected) { // 이미 하나가 선택된 상황이라면
			this.drawingTool.setSelected(anchors, this.selectedShape);
			isSelected = false;
		}
		for (GShapeTool shape : this.shapes) { // 선택된 도형 찾기
			if (shape.containes(x, y)) { // 만약 한 도형 내부에 속한거라면
				this.isSelected = true;
				this.selectedShape = shape;
			}
		}

		if (isSelected) {
			this.drawingTool.setSelected(anchors, this.selectedShape);
		}

	}

	private void setFinalPoint(int x, int y) {
		this.drawingTool.setFinalPoint(x, y);
		this.shapes.add(this.drawingTool);

	}

	////////////////////////////////////////////////////
	// inner classes
	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		private boolean isDrawing;

		public GMouseHandler() {
			isDrawing = false;

		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (shapeTool.getDrawingState() == EDrawingState.e2PointDrawing) {
				if (isDrawing) {
					animate(e.getX(), e.getY());
				}
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (shapeTool.getDrawingState() == EDrawingState.eNPointDrawing) {
				if (isDrawing) {
					animate(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (shapeTool.getDrawingState() == EDrawingState.eNPointDrawing) { // 폴리곤일때만
				if (e.getButton() == MouseEvent.BUTTON1) { // 마우스 왼쪽 클릭
					if (e.getClickCount() == 1) {
						if (isDrawing && !isSelected) { //선택된 상황이 아닐 때만 그리기
							setIntermediatePoint(e.getX(), e.getY());
						} else if (!isSelected) {
							setInitialPoint(e.getX(), e.getY());
							this.isDrawing = true;
						}

					} else if (e.getClickCount() == 2) {
						setFinalPoint(e.getX(), e.getY());
						this.isDrawing = false;
					}
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (shapeTool.getDrawingState() == EDrawingState.e2PointDrawing) {
				if (!isDrawing) { // 그리지 않고 있을 때
					if (!shapes.isEmpty()) { // 그린 게 있을 때
						setSelected(e.getX(), e.getY());
					}
					setInitialPoint(e.getX(), e.getY());
					this.isDrawing = true;
				}
			} else if (shapeTool.getDrawingState() == EDrawingState.eNPointDrawing) {
				if (!isDrawing) { // 그리지 않고 있을 때
					if (!shapes.isEmpty()) { // 그린 게 있을 때
						setSelected(e.getX(), e.getY());
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (shapeTool.getDrawingState() == EDrawingState.e2PointDrawing) {
				if (isDrawing) {
					setFinalPoint(e.getX(), e.getY());
					this.isDrawing = false;
				}

			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

}
