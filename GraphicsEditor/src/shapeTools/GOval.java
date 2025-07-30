package shapeTools;

import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingState;

public class GOval extends GShape {

	public GOval() {
		super(EDrawingState.e2PointDrawing);
		this.shape = new Ellipse2D.Float();
	}
	@Override
	public void setInitialPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(x, y, 0, 0);
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		
	}
	@Override
	public void animate(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		int width = (int) (x - ellipse.getX());
		int height = (int) (y - ellipse.getY());
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), width, height);
		
//		graphics2d.drawOval(x0, y0, x1-x0, y1-y0);
//		x1 = x;
//		y1 = y;
//		graphics2d.drawOval(x0, y0, x1-x0, y1-y0);

	}
	
}
