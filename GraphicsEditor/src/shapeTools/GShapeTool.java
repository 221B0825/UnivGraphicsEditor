package shapeTools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D.Double;
import java.io.Serializable;

import main.GConstants;
import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;

abstract public class GShapeTool implements Serializable, Cloneable {
	// attributes
	private static final long serialVersionUID = 1L;

	public enum EAnchors {
		x0y0, x0y1, x0y2, x1y0, x1y2, x2y0, x2y1, x2y2, RR
	}

	private EDrawingStyle eDrawingStyle;
	protected Shape shape;
	private Ellipse2D[] anchors;
	private boolean isSelected;
	private EAnchors selectedAnchor;
	private EAction eAction;
	private AffineTransform affineTransform;
	// working variables

	// constructors
	public GShapeTool(EDrawingStyle eDrawingStyle) {
		this.eAction = null;
		this.anchors = new Ellipse2D.Double[EAnchors.values().length];
		for (EAnchors eAnchor : EAnchors.values()) {
			this.anchors[eAnchor.ordinal()] = new Ellipse2D.Double();
		}
		this.isSelected = false;
		this.eDrawingStyle = eDrawingStyle;
		this.selectedAnchor = null;

		this.affineTransform = new AffineTransform();
		// 초기화
		this.affineTransform.setToIdentity();
	}

	// getters & setters
	public EDrawingStyle getDrawingStyle() {
		return this.eDrawingStyle;
	}
	private double getX() {
		return this.shape.getBounds().getX();
	}
	private double getY() {
		return this.shape.getBounds().getY();
	}

	private double getWidth() {
		return this.shape.getBounds().getWidth();

	}

	private double getHeight() {
		return this.shape.getBounds().getHeight();
	}

	public EAction getAction() {
		return this.eAction;
	}

	// methods
	public EAction containes(int x, int y) {
		if (this.isSelected) {
			for (int i = 0; i < this.anchors.length - 1; i++) {
				if (this.anchors[i].contains(x, y)) {
					this.selectedAnchor = EAnchors.values()[i]; // 앵커에서의 몇번째인지
					this.eAction = EAction.eResize;

				}
			}
			if (this.anchors[EAnchors.RR.ordinal()].contains(x, y)) {
				this.eAction = EAction.eRotate;
			}
		}
		if (this.shape.contains(x, y)) {
			this.eAction = EAction.eMove;
		}
		return this.eAction;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void move(Graphics2D graphics2d, int dx, int dy) {
		this.draw(graphics2d);
		this.affineTransform.translate(dx, dy);
		this.draw(graphics2d);
	}

	public void resize(Graphics2D graphics2d, GShapeTool selectedShape, int x, int y) {
		this.draw(graphics2d);

		// x,y 는  현재 좌표 - 이전 좌표
		double width = selectedShape.getWidth();
		double height = selectedShape.getHeight();
		double deltaW = 0;
		double deltaH = 0;
		double xFactor = 1.0;
		double yFactor = 1.0;

		// 여기서 결정됨, GResizer가 GShapeTool을 부르기 때문에
		// Anchor의 반대편을 기준점으로 증가해야 함
		switch (this.selectedAnchor) {
		case x0y0:// NW
			deltaW = -x;
			deltaH = -y;
			break;
		case x0y1:// W
			break;
		case x0y2:// SW
			break;
		case x1y0:// N
			break;
		case x1y2:// S
			break;
		case x2y0:// NE
			break;
		case x2y1:// E
			break;
		case x2y2:// SE
			deltaW = x;
			deltaH = y;
			break;
		default:
			break;

		}

		if (width > 0.0) {
			xFactor = deltaW / width + xFactor;
		}
		if (height > 0.0) {
			yFactor = deltaH / height + yFactor;
		}
		
		this.affineTransform.scale(xFactor,yFactor);
		this.draw(graphics2d);
	}


	private void drawAnchors(Graphics2D graphics) {
		int wAnchor = GConstants.wAnchor;
		int hAnchor = GConstants.hAnchor;

		Rectangle rectangle = this.shape.getBounds();
		int x0 = rectangle.x - wAnchor / 2; // 좌측
		int x1 = rectangle.x - wAnchor / 2 + rectangle.width / 2; // 중간
		int x2 = rectangle.x - wAnchor / 2 + rectangle.width; // 우측

		int y0 = rectangle.y - hAnchor / 2; // 상단
		int y1 = rectangle.y - hAnchor / 2 + rectangle.height / 2; // 중간
		int y2 = rectangle.y - hAnchor / 2 + rectangle.height; // 하단

		this.anchors[EAnchors.x0y0.ordinal()].setFrame(x0, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y1.ordinal()].setFrame(x0, y1, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y2.ordinal()].setFrame(x0, y2, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y0.ordinal()].setFrame(x1, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y2.ordinal()].setFrame(x1, y2, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y0.ordinal()].setFrame(x2, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y1.ordinal()].setFrame(x2, y1, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y2.ordinal()].setFrame(x2, y2, wAnchor, hAnchor);

		this.anchors[EAnchors.RR.ordinal()].setFrame(x1, y0 - 40, wAnchor, hAnchor);

		for (EAnchors eAnchor : EAnchors.values()) {
			Color color = graphics.getColor();
			// 비어있는 앵커
			graphics.setColor(Color.WHITE);
			graphics.fill(this.affineTransform.createTransformedShape(this.anchors[eAnchor.ordinal()]));
			// 원래 팬 색깔
			graphics.setColor(color);
			graphics.draw(this.affineTransform.createTransformedShape(this.anchors[eAnchor.ordinal()]));
		}
	}

	public void draw(Graphics2D graphics) {

		graphics.draw(this.affineTransform.createTransformedShape(this.shape));

		if (isSelected) {
			this.drawAnchors(graphics);
		}
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
