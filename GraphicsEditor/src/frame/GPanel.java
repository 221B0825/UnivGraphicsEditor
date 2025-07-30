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

import main.GConstants.EDrawingState;
import shapeTools.GShape;

public class GPanel extends JPanel {
	////////////////////////////////////////////////////
	// attributes

	private static final long serialVersionUID = 1L;

	// components
	private GMouseHandler mouseHandler;

	// association

	// working Objects
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape shapeClone;

	////////////////////////////////////////////////////
	// getters and setters
	public void setSelection(GShape shapeTool) {
		this.shapeTool = shapeTool;

	}

	// constructors
	public GPanel() {
		this.shapeTool = null;

		this.shapes = new Vector<GShape>();

		this.mouseHandler = new GMouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		this.addMouseWheelListener(this.mouseHandler);

	}

	public void initialize() {

	}
	// methods

	public void paint(Graphics graphics) {
		super.paint(graphics);

		for (GShape shape : this.shapes) {
			shape.draw(graphics);
		}

	}

	private void setInitialPoint(int x, int y) {
		this.shapeClone = this.shapeTool.clone();
		this.shapeClone.setInitialPoint(x, y);
	}

	private void setIntermediatePoint(int x, int y) {
		this.shapeClone.setIntermediatePoint(x, y);
	}

	private void animate(int x, int y) {
		// exclusive or mode
		Graphics2D graphics2D = (Graphics2D)this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		
		this.shapeClone.draw(graphics2D);

		this.shapeClone.animate(x, y);

		this.shapeClone.draw(graphics2D);
	}

	private void setFinalPoint(int x, int y) {
		this.shapeClone.setFinalPoint(x, y);
		this.shapes.add(this.shapeClone);

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
			if (shapeTool.getDrawingState() == EDrawingState.eNPointDrawing) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						if (isDrawing) {
							setIntermediatePoint(e.getX(), e.getY());
						} else {
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
				if (!isDrawing) {
					setInitialPoint(e.getX(), e.getY());
					this.isDrawing = true;
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
