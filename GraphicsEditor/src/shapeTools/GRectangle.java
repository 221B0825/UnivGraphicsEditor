package shapeTools;

import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EDrawingStyle;

public class GRectangle extends GShapeTool {
	//attributes
	private static final long serialVersionUID = 1L;
	
	// components
	
	//constructors
	public GRectangle() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Rectangle();
	}
	@Override
	public GShapeTool newInstance() {
		return new GRectangle();
	}
	
	//getters & setters

	//methods
	@Override
	public void setInitialPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x,y);		
	}

	@Override
	public void setFinalPoint(int x, int y) {
		
	}
	
	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	
}
