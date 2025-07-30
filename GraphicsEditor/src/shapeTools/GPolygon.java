package shapeTools;

import java.awt.Graphics2D;
import java.awt.Polygon;

import main.GConstants.EDrawingState;

public class GPolygon extends GShapeTool{
	private Polygon polygon;

	public GPolygon() {
		super(EDrawingState.eNPointDrawing);
		this.polygon = new Polygon();
	}
	
	@Override
	public void setInitialPoint(int x, int y) {
		this.polygon = new Polygon();
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y); //animate 안에서 polygon.xpoints[polygon.npoints -1 ] = x; 를 진행할 때에는 polygon 안에 두 개 이상의 점이 존재해야 함
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);
	}
	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		graphics2d.draw(this.polygon);
		Polygon polygon = this.polygon;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
		graphics2d.draw(this.polygon);
	}

}
