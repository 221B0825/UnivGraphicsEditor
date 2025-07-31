package shapeTools;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EDrawingState;

public class GRectangle extends GShapeTool {
	//attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private Rectangle rectangle;
	
	//constructors
	public GRectangle() {
		super(EDrawingState.e2PointDrawing);
		this.rectangle = new Rectangle();
	}
	@Override
	public GShapeTool newInstance() {
		return new GRectangle();
	}
	
	//getters & setters

	//methods
	@Override
	public void setInitialPoint(int x, int y) {
		this.rectangle.setLocation(x,y);
		this.rectangle.setSize(0,0);
		
	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.rectangle);
	}
	
	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
		this.draw(graphics2d);

	}

	
	
}
