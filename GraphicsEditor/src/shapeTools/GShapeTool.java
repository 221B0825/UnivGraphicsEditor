package shapeTools;

import java.awt.Graphics2D;
import java.io.Serializable;

import main.GConstants.EDrawingState;

abstract public class GShapeTool implements Serializable{
	//attributes
	protected int x0, y0, x1, y1;
	
	//working variables
	private EDrawingState eDrawingState;
	
	

	public GShapeTool(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
	}
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}
	public abstract GShapeTool clone();
	
	public void setInitialPoint(int x, int y) {
		x0 = x;
		y0 = y;
		x1 = x;
		y1 = y;

	}

	public void setIntermediatePoint(int x, int y) {

	}

	public void setFinalPoint(int x, int y) {
		x1 = x;
		y1 = y;

	}
	
	

	public abstract void animate(Graphics2D graphics2d, int x, int y);
	public abstract void draw(Graphics2D graphics2d);

}
