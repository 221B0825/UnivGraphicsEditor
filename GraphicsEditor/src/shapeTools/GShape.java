package shapeTools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.lang.reflect.InvocationTargetException;

import main.GConstants.EDrawingState;

abstract public class GShape {

	private EDrawingState eDrawingState;
	protected int x0, y0, x1, y1;
	protected Shape shape;

	public GShape(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
	}
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}

	public GShape clone() {
		try {
			return this.getClass().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public abstract void setInitialPoint(int x, int y);

	public abstract void setIntermediatePoint(int x, int y);

	public void setFinalPoint(int x, int y) {
		x1 = x;
		y1 = y;
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(this.shape);
	}

	abstract public void animate(int x, int y);
	

}
