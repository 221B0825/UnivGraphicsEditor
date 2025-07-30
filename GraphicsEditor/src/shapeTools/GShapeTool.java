package shapeTools;

import java.awt.Graphics2D;

import main.GConstants.EDrawingState;

abstract public class GShapeTool {

	private EDrawingState eDrawingState;
	protected int x0, y0, x1, y1;

	public GShapeTool(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
	}
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}


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

	abstract public void animate(Graphics2D graphics2d, int x, int y);

}
