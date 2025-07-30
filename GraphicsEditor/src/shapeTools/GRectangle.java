package shapeTools;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GConstants.EDrawingState;

public class GRectangle extends GShape {

	public GRectangle() {
		super(EDrawingState.e2PointDrawing);
		this.shape = new Rectangle();
	}
	@Override
	public void setInitialPoint(int x, int y) {
	
		Rectangle rectangle = (Rectangle)this.shape;
		rectangle.setLocation(x,y);
		rectangle.setSize(0,0);
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		
	}

	@Override
	public void animate(int x, int y) {
		Rectangle rectangle = (Rectangle)this.shape;
		int width = x-rectangle.x;
		int height = y-rectangle.y;
		rectangle.setSize(width,height);
		
//		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);
//		x1 = x;
//		y1 = y;
//		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);

	}
	

	

}
