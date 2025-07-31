package shapeTools;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import main.GConstants.EDrawingState;

public class GLine extends GShapeTool {

	private static final long serialVersionUID = 1L;

	public GLine() {
		super(EDrawingState.e2PointDrawing);
		this.shape = new Line2D.Float();
	}

	@Override
	public void setInitialPoint(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(x, y, x, y);

	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(line.getX1(), line.getY1(), x, y);
	}

	@Override
	public void setIntermediatePoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

}
