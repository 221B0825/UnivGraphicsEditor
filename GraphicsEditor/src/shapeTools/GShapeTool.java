package shapeTools;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import main.GConstants.EDrawingState;

abstract public class GShapeTool implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes
	protected Shape shape;
	
	
	//working variables
	private EDrawingState eDrawingState;

	public GShapeTool(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
	}
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}
	
	public GShapeTool clone() {
		try {
			return this.getClass().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void draw(Graphics2D graphics2d) {
		Graphics2D graphics2D = (Graphics2D) graphics2d;
		graphics2D.draw(this.shape);
	}
	
	public abstract void setInitialPoint(int x, int y);
	public abstract void animate(Graphics2D graphics2d, int x, int y);
	public abstract void setIntermediatePoint(int x, int y);
	public abstract void setFinalPoint(int x, int y);
}
