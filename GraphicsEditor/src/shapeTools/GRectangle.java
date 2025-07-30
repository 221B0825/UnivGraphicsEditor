package shapeTools;

import java.awt.Graphics2D;

import main.GConstants.EDrawingState;

public class GRectangle extends GShapeTool {

	public GRectangle() {
		super(EDrawingState.e2PointDrawing);
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);
		x1 = x;
		y1 = y;
		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);

	}

}
