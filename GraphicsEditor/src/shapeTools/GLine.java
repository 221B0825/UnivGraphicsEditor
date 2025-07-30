package shapeTools;

import java.awt.Graphics2D;

import main.GConstants.EDrawingState;

public class GLine extends GShapeTool {

	public GLine() {
		super(EDrawingState.e2PointDrawing);
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		graphics2d.drawLine(x0, y0, x1, y1);
		x1 = x;
		y1 = y;
		graphics2d.drawLine(x0, y0, x1, y1);

	}

}
