package shapeTools;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import main.GConstants.EDrawingState;

public class GLine extends GShapeTool {
	//attributes
	private static final long serialVersionUID = 1L;
	//components
	private Line2D line2d;
	public GLine() {
		super(EDrawingState.e2PointDrawing);
		this.line2d = new Line2D.Float();
	}

	@Override
	public GShapeTool newInstance() {
		return new GLine();
	}

	@Override
	public void setInitialPoint(int x, int y) {
		this.line2d.setLine(x, y, x, y);

	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.line2d);

	}

	@Override
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.line2d.setLine(this.line2d.getX1(), this.line2d.getY1(),x ,y);
		this.draw(graphics2d);

	}

}
