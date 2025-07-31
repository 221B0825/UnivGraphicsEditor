package shapeTools;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

import main.GConstants.EDrawingState;

abstract public class GShapeTool implements Serializable, Cloneable{
	//attributes
	private static final long serialVersionUID = 1L;
	
	//working variables
	private EDrawingState eDrawingState;
	
	// constructors
	public GShapeTool(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
	}
	
	// getters & setters
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}
	
	// interface
	public abstract GShapeTool newInstance();
	public abstract void setInitialPoint(int x, int y);
	public void setIntermediatePoint(int x, int y) {}
	public abstract void setFinalPoint(int x, int y);
	
	
	public abstract void draw(Graphics2D graphics2d);
	public abstract void animate(Graphics2D graphics2d, int x, int y);
	
	
	
}
