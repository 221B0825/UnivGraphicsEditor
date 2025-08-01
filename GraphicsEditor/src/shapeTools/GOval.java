package shapeTools;

import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingStyle;

public class GOval extends GShapeTool {
	//attributes
	private static final long serialVersionUID = 1L;
	//components
	public GOval() {
		super(EDrawingStyle.e2PointDrawing); 
		this.shape = new Ellipse2D.Float();
	}
	@Override
	public GShapeTool newInstance() {
		return new GOval();
		
	}
	@Override
	public void setInitialPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(x, y, 0, 0);
		//this.draw(this.ellipse);		
	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(ellipse.getX(),ellipse.getY(), x-ellipse.getX(), y-ellipse.getY());
	
	}

}
