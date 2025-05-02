package shapeTools;

import java.awt.geom.Line2D;

import main.GConstants.EDrawingStyle;

public class GLine extends GShapeTool {
	private static final long serialVersionUID = 1L;

	public GLine() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Line2D.Float();
	}
	
	public boolean contains(int x, int y) {
		Line2D line = (Line2D) this.shape;
		if (line.ptSegDist(x, y) < 5) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public GShapeTool newInstance() {
		return new GLine();
	}

	@Override
	public void setInitialPoint(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(x, y, x, y);
	}

	@Override
	public void movePoint(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(line.getX1(), line.getY1(), x, y);
	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveShape(int dx, int dy) {
		Line2D line = (Line2D) this.shape;
		line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
	}

}
