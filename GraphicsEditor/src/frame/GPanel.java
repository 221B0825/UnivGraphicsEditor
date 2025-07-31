package frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EDrawingStyle;
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
	private GShapeTool selectedTool;

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

	private boolean onShape(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setXORMode(getBackground());
		
		for (GShapeTool shapeTool : this.shapes) {
			if (shapeTool.containes(x, y)) {
				this.selectedTool = shapeTool;
				this.drawingTool = shapeTool;
				this.drawingTool.setSelected(graphics2D,true);
				return true;
			}
		}
		
		for (GShapeTool shapeTool : this.shapes) { // 도형 밖을 선택하면 전체 지움
			shapeTool.setSelected(graphics2D,false);
		}
		return false;
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
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (e.getClickCount() == 1) {
					this.mouseLButton1Clicked(e);
				} else if (e.getClickCount() == 2) {
					this.mouseLButton2Clicked(e);
				}

			} else if (e.getButton() == MouseEvent.BUTTON2) {
				if (e.getClickCount() == 1) {
					this.mouseRButton1Clicked(e);
				}
			}
		}

		private void mouseLButton1Clicked(MouseEvent e) {
			if (!isDrawing) {
				if (!onShape(e.getX(), e.getY())) {
					if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
						setInitialPoint(e.getX(), e.getY());
						this.isDrawing = true;
					}
				}
					
			} else {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					setIntermediatePoint(e.getX(), e.getY());
				}
			}
		}

		private void mouseLButton2Clicked(MouseEvent e) {
			if (isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					setFinalPoint(e.getX(), e.getY());
					this.isDrawing = false;
				}
			}
		}

		private void mouseRButton1Clicked(MouseEvent e) {

		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
				if (isDrawing) {
					animate(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					setInitialPoint(e.getX(), e.getY());
					this.isDrawing = true;
				}

			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					animate(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
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
