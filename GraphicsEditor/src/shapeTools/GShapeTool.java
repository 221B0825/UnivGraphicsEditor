package shapeTools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import main.GConstants.CAnchor;
import main.GConstants.EAnchors;
import main.GConstants.EDrawingStyle;

abstract public class GShapeTool implements Serializable, Cloneable {
	// attributes
	private static final long serialVersionUID = 1L;
	
	public int moveX, moveY;
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
	public boolean contains(int x, int y) {
		return this.shape.contains(x, y);
	}

	public void setSelected(Graphics2D graphics2d, boolean isSelected) {
		if (isSelected) { // true면 그리라고 하는 것, false면 지우라고 하는 것
			if (!this.isSelected) { // 그려져 있지 않으면 그림
				setAnchors(this.shape.getBounds());
				drawAnchors(graphics2d);

			}
		} else { // false 일때
			if (this.isSelected) { // 그려져 있을 때 지움
				setAnchors(this.shape.getBounds());
				drawAnchors(graphics2d);
			}
		}
		this.isSelected = isSelected;
	}
	// ----------------------------------------------
	
	private void setAnchors(Rectangle rectangle) {
		
		int x0 = rectangle.x - CAnchor.wAnchor / 2; // 좌측
		int x1 = rectangle.x - CAnchor.wAnchor / 2 + rectangle.width / 2; // 중간
		int x2 = rectangle.x - CAnchor.wAnchor / 2 + rectangle.width; // 우측

		int y0 = rectangle.y - CAnchor.hAnchor / 2; // 상단
		int y1 = rectangle.y - CAnchor.hAnchor / 2 + rectangle.height / 2; // 중간
		int y2 = rectangle.y - CAnchor.hAnchor / 2 + rectangle.height; // 하단

		this.anchors[EAnchors.x0y0.ordinal()].setFrame(x0, y0, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x0y1.ordinal()].setFrame(x0, y1, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x0y2.ordinal()].setFrame(x0, y2, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x1y0.ordinal()].setFrame(x1, y0, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x1y2.ordinal()].setFrame(x1, y2, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x2y0.ordinal()].setFrame(x2, y0, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x2y1.ordinal()].setFrame(x2, y1, CAnchor.wAnchor, CAnchor.hAnchor);
		this.anchors[EAnchors.x2y2.ordinal()].setFrame(x2, y2, CAnchor.wAnchor, CAnchor.hAnchor);

		this.anchors[EAnchors.RR.ordinal()].setFrame(x1, y0 - 40, CAnchor.wAnchor, CAnchor.hAnchor);
	}
	public void drawAnchors(Graphics2D graphics2d) {
		for (EAnchors eAnchor : EAnchors.values()) {
			// 비어있는 앵커를 만드려고 함
			Color color = graphics2d.getColor(); //펜 색깔을 color에 저장함
			
			graphics2d.setColor(graphics2d.getBackground()); //펜 색깔을 배경색으로 바꿈
			graphics2d.fill(this.anchors[eAnchor.ordinal()]); //배경색으로 속을 칠함
			
			graphics2d.setColor(color); //원래 펜 색깔로 바꿔줌
			graphics2d.draw(this.anchors[eAnchor.ordinal()]); //원래 펜 색깔로 그림
		}
	}

	public boolean onAnchor(int x, int y) {
		for (Ellipse2D anchor : this.anchors) {
			if (anchor.contains(x, y)) {
				return true;
			}
		}
		return false;
	}
	// ----------------------------------------------
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
	}

	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.movePoint(x, y);
		this.draw(graphics2d);
	}

	// ----------------------------------------------
	public void initMove(int x, int y) {
		this.moveX = x;
		this.moveY = y;
	}

	public void keepMove(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.moveShape(x-this.moveX,y-this.moveY);
		this.drawAnchors(graphics2d);
		this.setAnchors(this.shape.getBounds());
		this.drawAnchors(graphics2d);
		this.moveX = x;
		this.moveY = y;
		this.draw(graphics2d);
	}

	public void finishMove(int x, int y) {

	}

	// ----------------------------------------------
	// interface
	public abstract GShapeTool newInstance();

	public abstract void setInitialPoint(int x, int y);

	public void setIntermediatePoint(int x, int y) {
	}

	public abstract void setFinalPoint(int x, int y);

	public abstract void movePoint(int x, int y);
	
	// ----------------------------------------------
	public abstract void moveShape(int x, int y);

}
