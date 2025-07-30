package shapeTools;

import java.awt.Graphics2D;

import main.GConstants.EDrawingState;

public class GOval extends GShapeTool {

	public GOval() {
		super(EDrawingState.e2PointDrawing); 
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		graphics2d.drawOval(x0, y0, x1-x0, y1-y0);
		x1 = x;
		y1 = y;
		graphics2d.drawOval(x0, y0, x1-x0, y1-y0);


	}

}
