package transformer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import shapeTools.GShapeTool;

public class GResizer extends GTransformer {
	
	public GResizer(GShapeTool selectedShape) {
		super(selectedShape);
	}

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.selectedShape.initTransform(graphics2d,x - px, y - py);
		this.px = x;
		this.py = y;

	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		this.selectedShape.resize(graphics2d, x - px, y - py);
		
	}

}
