package shapeTools;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import main.GConstants.EDrawingStyle;

abstract public class GShapeTool implements Serializable, Cloneable {
	// attributes
	private static final long serialVersionUID = 1L;

	public enum EAnchors {
		x0y0, x0y1, x0y2, x1y0, x1y2, x2y0, x2y1, x2y2, RR
	}

	public final int wAnchor = 10;
	public final int hAnchor = 10;

	private EDrawingStyle eDrawingStyle;
	protected Shape shape;
	private Ellipse2D[] anchors;
	private boolean isSelected;

	// working variables

	// constructors
	public GShapeTool(EDrawingStyle eDrawingStyle) {

		this.anchors = new Ellipse2D.Double[EAnchors.values().length];
		for (EAnchors eAnchor : EAnchors.values()) {
			this.anchors[eAnchor.ordinal()] = new Ellipse2D.Double();
		}
		this.isSelected = false;
		this.eDrawingStyle = eDrawingStyle;
	}

	// getters & setters
	public EDrawingStyle getDrawingStyle() {
		return this.eDrawingStyle;
	}

	// methods
	public boolean containes(int x, int y) {
		return this.shape.contains(x, y);// 얘가 true면 setSelected 함수 만들고 여기에서 anchor그림
	}

	public void setSelected(Graphics2D graphics, boolean isSelected) {
		if (isSelected) { // true면 그리라고 하는 것, false면 지우라고 하는 것
			if (!this.isSelected) { // 그려져 있지 않으면 그림
				drawAnchors(graphics);
			}
		} else { // false 일때
			if (this.isSelected) { // 그려져 있을 때 지움
				drawAnchors(graphics);
			}
		}
		this.isSelected = isSelected;
	}

	private void drawAnchors(Graphics2D graphics) {
		Rectangle rectangle = this.shape.getBounds();
		int x0 = rectangle.x; // 좌측
		int x1 = rectangle.x + (rectangle.width) / 2; // 중간
		int x2 = rectangle.x + rectangle.width; // 우측

		int y0 = rectangle.y; // 상단
		int y1 = rectangle.y + (rectangle.height) / 2; // 중간
		int y2 = rectangle.y + rectangle.height; // 하단

		this.anchors[EAnchors.x0y0.ordinal()].setFrame(x0-5, y0-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y1.ordinal()].setFrame(x0-5, y1-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y2.ordinal()].setFrame(x0-5, y2-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y0.ordinal()].setFrame(x1-5, y0-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y2.ordinal()].setFrame(x1-5, y2-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y0.ordinal()].setFrame(x2-5, y0-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y1.ordinal()].setFrame(x2-5, y1-5, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y2.ordinal()].setFrame(x2-5, y2-5, wAnchor, hAnchor);

		this.anchors[EAnchors.RR.ordinal()].setFrame(x1-5, y0 - 40, wAnchor, hAnchor);

		for (EAnchors eAnchor : EAnchors.values()) {
			graphics.draw(this.anchors[eAnchor.ordinal()]);
		}
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

	public void setIntermediatePoint(int x, int y) {
	}

	public abstract void setFinalPoint(int x, int y);

	public abstract void movePoint(int x, int y);

}
