package shapeTools;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import main.GConstants.EDrawingState;

public class GPolygon extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	
	public GPolygon() {
		super(EDrawingState.eNPointDrawing);
		this.polygon = new Polygon();
	}
	@Override
	public GShapeTool newInstance() {
		return new GPolygon();
		
	}

	@Override
	public void setInitialPoint(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void setFinalPoint(int x, int y) {
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.polygon);
	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
		this.draw(graphics2d);
	}
	

}
