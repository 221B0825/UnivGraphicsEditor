package shapeTools;

import java.awt.Graphics2D;
import java.awt.Polygon;

import main.GConstants.EDrawingState;

public class GPolygon extends GShapeTool {
	private static final long serialVersionUID = 1L;

	public GPolygon() {
		super(EDrawingState.eNPointDrawing);
		this.shape = new Polygon(); 
	}
	@Override
	public void setInitialPoint(int x, int y) {
		Polygon polygon = (Polygon)this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		Polygon polygon = (Polygon)this.shape;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		Polygon polygon = (Polygon)this.shape;
		polygon.addPoint(x, y);
	}


	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
}
