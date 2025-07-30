package main;

import java.awt.Graphics2D;

public class GRectangle extends GShapeTool {

	public GRectangle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D graphics2d, int x, int y) {
		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);
		x1 = x;
		y1 = y;
		graphics2d.drawRect(x0, y0, x1-x0, y1-y0);

	}

}
