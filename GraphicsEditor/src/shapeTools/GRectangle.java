package shapeTools;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GConstants.EDrawingState;

public class GRectangle extends GShapeTool {
	private static final long serialVersionUID = 1L;
	public GRectangle() {
		super(EDrawingState.e2PointDrawing);
		this.shape = new Rectangle();
	}
	
	@Override
	public void setInitialPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x,y);
		rectangle.setSize(0,0);
		
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		Rectangle rectangle = (Rectangle)this.shape;
		int width = x-rectangle.x;
		int height = y-rectangle.y;
		rectangle.setSize(width,height);

	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIntermediatePoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
