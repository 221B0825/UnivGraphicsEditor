package transformer;

import java.awt.Graphics2D;

import shapeTools.GShapeTool;

public abstract class GTransformer {

	protected GShapeTool selectedShape;
	protected int px, py;

	public GTransformer(GShapeTool selectedShape) {
		this.selectedShape = selectedShape;
	}

	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.px = x;
		this.py = y;
	}

	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		// transform 안에서 지우고 그림
		this.transform(graphics2d, x, y);
		this.px = x;
		this.py = y;
	}

	public void finishTransforming(Graphics2D graphics2d, int x, int y) {

	}

	public void continueTransforming(Graphics2D graphics2d, int x, int y) {

	}

	abstract public void transform(Graphics2D graphics2d, int x, int y);
}
