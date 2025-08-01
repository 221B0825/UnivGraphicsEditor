package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;
import shapeTools.GShapeTool;
import transformer.GMover;
import transformer.GResizer;
import transformer.GTransformer;

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
	private GShapeTool selectedShape; // 그걸 카피해서 그림그리는 애
	private GTransformer transformer;
	
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
		this.setBackground(Color.WHITE);
	}
	// methods

	public void paint(Graphics graphics) {
		super.paint(graphics);// 부모가 원래 해야할 일들을 함, 자기를 칠하는 건데 부모가 먼저 해야 할 일들을 함(백그라운드 칠하기 등)
		for (GShapeTool shape : this.shapes) {
			shape.draw((Graphics2D) graphics);
		}
	}

	private void setSelected(GShapeTool selectedShape) {
		for(GShapeTool shape : this.shapes) {
			shape.setSelected(false);
		}
		this.selectedShape = selectedShape;
		this.selectedShape.setSelected(true);
		this.repaint();
	}

	private GShapeTool onShape(int x, int y) { //어떤 도형인지 확인함
		for (GShapeTool shape : this.shapes) {
			EAction eAction = shape.containes(x, y);
			if (eAction != null) {
				return shape;
			}
		}
		return null;
	}

	private void initDrawing(int x, int y) {
		this.selectedShape = this.shapeTool.newInstance();
		this.selectedShape.setInitialPoint(x, y);
	}

	private void setIntermediatePoint(int x, int y) {
		this.selectedShape.setIntermediatePoint(x, y);
	}

	private void keepDrawing(int x, int y) {
		// exclusive or mode
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setXORMode(getBackground());
		// erase
		this.selectedShape.animate(graphics2D, x, y);
	}

	private void finishDrawing(int x, int y) {
		this.selectedShape.setFinalPoint(x, y);
		this.shapes.add(this.selectedShape);

	}

	private void initTransforming(GShapeTool selectedShape,int x, int y) {

		this.selectedShape = selectedShape;
		EAction eAction = this.selectedShape.getAction();
		switch(eAction) {
		case eMove:
			this.transformer = new GMover(this.selectedShape);
			break;
		case eResize:
			this.transformer = new GResizer(this.selectedShape);
			break;
		case eRotate:
			break;
			//셋 중 하나도 아닐 떄
		default:
			break;
		}
		
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transformer.initTransforming(graphics2d,x,y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		
		this.transformer.keepTransforming(graphics2d, x, y);
	}

	private void finishTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		
		this.transformer.finishTransforming(graphics2d, x, y);
	}

	////////////////////////////////////////////////////
	// inner classes
	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		private boolean isDrawing;
		private boolean isTransforming;

		public GMouseHandler() {
			isDrawing = false;
			isTransforming = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!isDrawing) {
				GShapeTool selectedShape = onShape(e.getX(), e.getY());
				if (selectedShape == null) {
					if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
						initDrawing(e.getX(), e.getY());
						this.isDrawing = true;
					}
				} else {
					initTransforming(selectedShape,e.getX(), e.getY());
					this.isTransforming = true;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					keepDrawing(e.getX(), e.getY());
				}
			} else if (this.isTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					finishDrawing(e.getX(), e.getY());
					this.isDrawing = false;
				}
			} else if (this.isTransforming) {
				finishTransforming(e.getX(), e.getY());
				this.isTransforming = false;
			}
		}

		private void mouseLButton1Clicked(MouseEvent e) {
			if (!isDrawing) {
				GShapeTool selectedShape = onShape(e.getX(), e.getY());
				if (selectedShape == null) {
					if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
						initDrawing(e.getX(), e.getY());
						this.isDrawing = true;
					}
				} else { // 도형을 클릭한 경우 앵커 그리기
					setSelected(selectedShape);
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
					finishDrawing(e.getX(), e.getY());
					this.isDrawing = false;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
				if (isDrawing) {
					keepDrawing(e.getX(), e.getY());
				}
			}
		}

		private void mouseRButton1Clicked(MouseEvent e) {
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
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

	}

}
