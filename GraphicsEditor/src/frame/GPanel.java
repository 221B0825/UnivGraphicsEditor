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

import main.GConstants.EAnchors;
import main.GConstants.EDrawingState;
import main.GConstants.EDrawingStyle;
import shapeTools.GShapeTool;

public class GPanel extends JPanel {
	// ----------------------------------------------
	// attributes

	private static final long serialVersionUID = 1L;

	// components
	private GMouseHandler mouseHandler;
	private Vector<GShapeTool> shapes;

	// association

	// working Objects
	private EDrawingState eDrawingState; // 멈춰있거나 그리고 있거나 동작하고 있거나를 판별하는 애
	private GShapeTool shapeTool; // 도구에 선택된 애가 있음
	private GShapeTool drawingTool; // 그걸 카피해서 그림그리는 애
	private int selectedIndex;

	private Vector<GShapeTool> selectedShapes; // 선택된 도형들

	// ----------------------------------------------
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
		this.selectedShapes = new Vector<GShapeTool>();

		this.eDrawingState = eDrawingState.eStop;

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
	// ----------------------------------------------
	private boolean onShape(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		graphics2d.setXORMode(getBackground());
		graphics2d.setBackground(getBackground());
		
		for (GShapeTool shapeTool : this.shapes) {
			if (shapeTool.contains(x, y)) {
				this.drawingTool = shapeTool;
				this.selectedShapes.add(shapeTool);
				this.drawingTool.setSelected(graphics2d, true);
				return true;
			}
		}
		if (!this.selectedShapes.isEmpty()) { //선택된 도형이 있다면
			if (!onAnchor(x, y)) { // 앵커(도형 밖 포함)를 클릭하지 않았을 경우
				for (GShapeTool shapeTool : this.selectedShapes) { //앵커 전체 지움
					this.drawingTool = shapeTool;
					this.drawingTool.setSelected(graphics2d, false);
				}
				this.selectedShapes.clear();
			}
		}
		return false;
	}

	public boolean onAnchor(int x, int y) { // 앵커가 그려진 도형은 선택된 도형들임
		for (GShapeTool shapeTool : this.selectedShapes) {
			if (shapeTool.onAnchor(x, y)) { // 선택된 도형들의 앵커들 중 하나만 클릭해도 참
				this.selectedIndex = selectedShapes.indexOf(shapeTool);
				return true;
			}
		}
		return false;
	}

	// ----------------------------------------------
	private void setInitialPoint(int x, int y) {
		this.drawingTool = this.shapeTool.newInstance();
		this.drawingTool.setInitialPoint(x, y);
	}

	private void setIntermediatePoint(int x, int y) {
		this.drawingTool.setIntermediatePoint(x, y);
	}

	private void animate(int x, int y) {
		// exclusive or mode
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		graphics2d.setXORMode(getBackground());
		graphics2d.setBackground(getBackground());
		// erase
		this.drawingTool.animate(graphics2d, x, y);
	}

	private void setFinalPoint(int x, int y) {
		this.drawingTool.setFinalPoint(x, y);
		this.shapes.add(this.drawingTool);

	}

	// ----------------------------------------------
	private void initMove(int x, int y) {
		for (GShapeTool shapeTool : this.selectedShapes) {
			this.drawingTool = shapeTool;
			this.drawingTool.initMove(x, y);
		}
	}

	private void keepMove(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		graphics2d.setXORMode(getBackground());
		graphics2d.setBackground(getBackground());

		for (GShapeTool shapeTool : this.selectedShapes) {
			this.drawingTool = shapeTool;
			this.drawingTool.keepMove(graphics2d, x, y);
		}
	}

	private void finishMove(int x, int y) {
		for (GShapeTool shapeTool : this.selectedShapes) {
			this.drawingTool = shapeTool;
			this.drawingTool.finishMove(x, y);
			this.shapes.add(this.drawingTool);
		}
	}

	// ----------------------------------------------
	// inner classes
	private class GMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		public GMouseHandler() {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eStop) {
				if (!onShape(e.getX(), e.getY())) { // 도형 위를 누르지 않았을 때
					if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
						setInitialPoint(e.getX(), e.getY());
						eDrawingState = EDrawingState.eDrawing;
					}
				} else { // 도형 위를 눌렀을 때 - 앵커가 그려져 있음 - onShape의 과정으로 그려짐
					if (!onAnchor(e.getX(), e.getY())) { // 앵커 위가 아닌 도형 위인지 판별해야 함
						initMove(e.getX(), e.getY());
						eDrawingState = EDrawingState.eMove;
					} else { // 앵커 위일때 이미 한번 판별했으므로 어떤 앵커인지 가져올 수 있음
						if(selectedIndex==0) {
							
						}
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					animate(e.getX(), e.getY());
				}
			} else if (eDrawingState == EDrawingState.eMove) {
				keepMove(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					setFinalPoint(e.getX(), e.getY());
					eDrawingState = EDrawingState.eStop;
				}
			} else if (eDrawingState == EDrawingState.eMove) {
				finishMove(e.getX(), e.getY());
				eDrawingState = EDrawingState.eStop;
			}
		}

		private void mouseLButton1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eStop) {
				if (!onShape(e.getX(), e.getY())) {
					if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
						setInitialPoint(e.getX(), e.getY());
						eDrawingState = EDrawingState.eDrawing;
					}
				}
			} else {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					setIntermediatePoint(e.getX(), e.getY());
				}
			}
		}

		private void mouseLButton2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					setFinalPoint(e.getX(), e.getY());
					eDrawingState = EDrawingState.eStop;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
				if (eDrawingState == EDrawingState.eDrawing) {
					animate(e.getX(), e.getY());
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
