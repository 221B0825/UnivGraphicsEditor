package transformer;

import java.awt.Graphics2D;

import shapeTools.GShapeTool;

public class GResizer extends GTransformer {

	public GResizer(GShapeTool selectedShape) {
		super(selectedShape);
	}

	@Override
	public void transform(Graphics2D graphics2d, int x, int y) {
		this.selectedShape.resize(graphics2d,this.selectedShape ,x-px, y-py);
		
	}

}
