package shapeTools;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EDrawingStyle;

public class GPolygon extends GShapeTool {
	private static final long serialVersionUID = 1L;
	
	
	public GPolygon() {
		super(EDrawingStyle.eNPointDrawing);
		this.shape = new Polygon();
	}
	public Object clone() {
		GShapeTool cloned = (GShapeTool) super.clone();
		Polygon polygon = new Polygon();
		
		Polygon typeCasting = (Polygon) this.shape;
		
		//루핑 돌면서 죄표 옮겨줘야 함
		for(int i=0; i<typeCasting.npoints;i++) {
			 polygon.addPoint(typeCasting.xpoints[i],typeCasting.ypoints[i]);
		}
		
		//this.shape => polygon value copy
		// polygon.add(this.shape.x[i],this.shape.y[i]);
		
		cloned.shape = polygon; 	
		return cloned;
	}

	@Override
	public void setInitialPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void setIntermediatePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}

	@Override
	public void setFinalPoint(int x, int y) {
		
	}

	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	
}
