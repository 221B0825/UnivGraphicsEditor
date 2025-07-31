package shapeTools;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingState;

public class GOval extends GShapeTool {
	//attributes
	private static final long serialVersionUID = 1L;
	//components
	private Ellipse2D ellipse;
	public GOval() {
		super(EDrawingState.e2PointDrawing); 
		this.ellipse = new Ellipse2D.Float();
	}
	@Override
	public GShapeTool newInstance() {
		return new GOval();
		
	}
	@Override
	public void setInitialPoint(int x, int y) {
		this.ellipse.setFrame(x, y, 0, 0);
		//this.draw(this.ellipse);
	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.ellipse);
	}
	
	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
		
		this.draw(graphics2d);

	}

}
