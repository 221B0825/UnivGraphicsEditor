package shapeTools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

import main.GConstants;
import main.GConstants.CAnchor;
import main.GConstants.EDrawingState;

abstract public class GShapeTool implements Serializable, Cloneable{
	//attributes
	private static final long serialVersionUID = 1L;
	
	protected Shape shape;
	private Shape[] anchors;
	
	//working variables
	private EDrawingState eDrawingState;
	
	// constructors
	public GShapeTool(EDrawingState eDrawingState) {
		this.eDrawingState = eDrawingState;
		this.anchors = new Shape[10]; //총 10개의 anchors
	
	}
	
	// getters & setters
	public EDrawingState getDrawingState() {
		return this.eDrawingState;
	}
	public Shape getShape() {
		return this.shape;
	}
	// methods
	public boolean containes(int x, int y) {
		return this.shape.contains(x,y);
	}
	public void setSelected(Graphics2D graphics2d, GShapeTool selectedShape){
			// draw anchors
			Rectangle rectangle = selectedShape.getShape().getBounds();
			
			//------------------------------------------------------------------------------------------
			//resize anchors
				//오른쪽 위
			Ellipse2D dot = new Ellipse2D.Double();
			graphics2d.setColor(Color.red);
			
			dot.setFrame(CAnchor.getVertex(rectangle.getMinX(),rectangle.getMinY()),CAnchor.dimension);
			this.anchors[0] = dot;
			graphics2d.draw(this.anchors[0]);
			
				//왼쪽 위
			dot.setFrame(CAnchor.getVertex(rectangle.getMaxX(),rectangle.getMinY()),CAnchor.dimension);
			this.anchors[1] = dot;
			graphics2d.draw(this.anchors[1]);
				//오른쪽 아래
			dot.setFrame(CAnchor.getVertex(rectangle.getMinX(),rectangle.getMaxY()),CAnchor.dimension);
			this.anchors[2] = dot;
			graphics2d.draw(this.anchors[2]);
				//왼쪽 아래
			dot.setFrame(CAnchor.getVertex(rectangle.getMaxX(),rectangle.getMaxY()),CAnchor.dimension);
			this.anchors[3]= dot;
			graphics2d.draw(this.anchors[3]);
			//------------------------------------------------------------------------------------------
				//위 중점
			dot.setFrame(CAnchor.getPoint(rectangle.getCenterX(),rectangle.getMinY()-5),CAnchor.dimension);
			this.anchors[4] = dot;
			graphics2d.draw(this.anchors[4]);
				//아래 중점
			dot.setFrame(CAnchor.getPoint(rectangle.getCenterX(),rectangle.getMaxY()-5),CAnchor.dimension);
			this.anchors[5] = dot;
			graphics2d.draw(this.anchors[5]);
				//왼쪽 중점
			dot.setFrame(CAnchor.getPoint(rectangle.getMinX()-5,rectangle.getCenterY()),CAnchor.dimension);
			this.anchors[6] = dot;
			graphics2d.draw(this.anchors[6]);
				//오른쪽 중점
			dot.setFrame(CAnchor.getPoint(rectangle.getMaxX()-5,rectangle.getCenterY()),CAnchor.dimension);
			this.anchors[7] = dot;
			graphics2d.draw(this.anchors[7]);
			//------------------------------------------------------------------------------------------
				// move anchor
			dot.setFrame(CAnchor.getPoint(rectangle.getCenterX(),rectangle.getCenterY()),CAnchor.dimension);
			this.anchors[8] = dot;
			graphics2d.draw(this.anchors[8]);
				// rotate anchor
			dot.setFrame(CAnchor.getRotatePoint(rectangle.getCenterX(),rectangle.getMinY()),CAnchor.dimension);
			this.anchors[9] = dot;
			graphics2d.draw(this.anchors[9]);
			
	}
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
	}
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.movePoint(x, y);
		this.draw(graphics2d);
	}
	
	
	// interface
	public abstract GShapeTool newInstance();
	public abstract void setInitialPoint(int x, int y);
	public void setIntermediatePoint(int x, int y) {}
	public abstract void setFinalPoint(int x, int y);
	public abstract void movePoint(int x, int y);
	
}
