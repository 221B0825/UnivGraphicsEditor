package main;

import java.awt.Graphics2D;

public class GLine extends GShapeTool {

	@Override
	public void draw(Graphics2D graphics2d, int x, int y) {
		graphics2d.drawLine(x0, y0, x1, y1);
		// new paint
		x1 = x;
		y1 = y;
		graphics2d.drawLine(x0, y0, x1, y1);

	}


}
