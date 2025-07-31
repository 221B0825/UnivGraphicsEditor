package shapeTools;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingState;

public class GOval extends GShapeTool {
	private static final long serialVersionUID = 1L;
	public GOval() {
		super(EDrawingState.e2PointDrawing); 
		this.shape = new Ellipse2D.Float();
	}
	
	@Override
	public void setInitialPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(x, y, 0, 0);
	}
	
	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		int width = (int) (x - ellipse.getX());
		int height = (int) (y - ellipse.getY());
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), width, height);
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
