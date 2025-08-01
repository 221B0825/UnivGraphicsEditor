package shapeTools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.io.Serializable;
import java.util.Vector;

import main.GConstants;
import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;

abstract public class GShapeTool implements Serializable, Cloneable {
	// attributes
	private static final long serialVersionUID = 1L;

	public enum EAnchors {
		x0y0, x0y1, x0y2, x1y0, x1y2, x2y0, x2y1, x2y2, RR
	}

	private EDrawingStyle eDrawingStyle; // 프리미티브 타입이라서 복사가 됨(정확한가?)
	protected Shape shape; // 복사가 안됨 얘는 복합 타입이라
	private Ellipse2D[] anchors; // 복사 안됨
	private boolean isSelected; // 복사 됨
	private EAnchors selectedAnchor;
	private EAction eAction;
	private AffineTransform affineTransform; // 복사 안됨(포인터만 복사됨)
	// 여기 있는 게 메모리의 값을 가지고 있느냐, 포인터만 있느냐의 차이임
	// working variables

	// constructors
	public GShapeTool(EDrawingStyle eDrawingStyle) {

		this.anchors = new Ellipse2D.Double[EAnchors.values().length];
		for (EAnchors eAnchor : EAnchors.values()) {
			this.anchors[eAnchor.ordinal()] = new Ellipse2D.Double();
		}
		this.isSelected = false;
		this.eDrawingStyle = eDrawingStyle;
		this.selectedAnchor = null;

		this.affineTransform = new AffineTransform();
		this.affineTransform.setToIdentity();
		
	}

	public Object clone() {
		GShapeTool cloned = null;
		try {
			cloned = (GShapeTool) super.clone(); // 이 위에있는 구조를 모두 복사해서 갖다줘라, 나까지는 복사가 됐는데,
			for (EAnchors eAnchor : EAnchors.values()) {
				cloned.anchors[eAnchor.ordinal()] = (Ellipse2D) this.anchors[eAnchor.ordinal()].clone();
			}
			cloned.affineTransform = (AffineTransform) this.affineTransform.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cloned;
	}

	// getters & setters
	public Shape getShape() {
		return this.shape;
	}

	public Shape setShape(Shape shape) {
		return this.shape = shape;
	}

	public EDrawingStyle getDrawingStyle() {
		return this.eDrawingStyle;
	}

	public EAction getAction() {
		return this.eAction;
	}

	// methods
	public EAction containes(int x, int y) {
		this.eAction = null;
		if (this.isSelected) {
			for (int i = 0; i < this.anchors.length - 1; i++) {
				if (this.affineTransform.createTransformedShape(this.anchors[i]).contains(x, y)) {
					this.selectedAnchor = EAnchors.values()[i];
					this.eAction = EAction.eResize;
				}
			}
			if (this.affineTransform.createTransformedShape(this.anchors[EAnchors.RR.ordinal()]).contains(x, y)) {
				this.eAction = EAction.eRotate;
			}
		}
		if (this.affineTransform.createTransformedShape(this.shape).contains(x, y)) {
			this.eAction = EAction.eMove;
		}
		return this.eAction;
	}

	public void setSelected(boolean isSelected) {

		this.isSelected = isSelected;
	}

	public void initTransform(Graphics2D graphics2d, int x, int y) {

	}

	public void move(Graphics2D graphics2d, int dx, int dy) {
		this.draw(graphics2d);
		this.affineTransform.translate(dx, dy);
		this.draw(graphics2d);
	}

	public void resize(Graphics2D graphics2d, double dx, double dy) {
		this.draw(graphics2d);
		
		Rectangle bound = this.shape.getBounds();
		
		dx = dx / bound.getWidth();
		dy = dy / bound.getHeight();

		switch (this.selectedAnchor) {
		case x0y0:
			this.affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY() + bound.getHeight()); //중심점 잡고
			this.affineTransform.scale(1 - dx, 1 - dy); //scale
			this.affineTransform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY() + bound.getHeight())); //옮겨진 위치 다시 잡아주기
			break;

		case x0y1:
			this.affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), 0);
			this.affineTransform.scale(1 - dx, 1);
			this.affineTransform.translate(-(bound.getMinX() + bound.getWidth()), 0);
			break;

		case x0y2:
			this.affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY());
			this.affineTransform.scale(1 - dx, 1 + dy);
			this.affineTransform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY()));
			break;

		case x1y0:
			this.affineTransform.setToTranslation(0, bound.getMinY() + bound.getHeight());
			this.affineTransform.scale(1, 1 - dy);
			this.affineTransform.translate(0, -(bound.getMinY() + bound.getHeight()));
			break;

		case x1y2:
			this.affineTransform.setToTranslation(0, bound.getMinY());
			this.affineTransform.scale(1, 1 + dy);
			this.affineTransform.translate(0, -(bound.getMinY()));
			break;

		case x2y0:
			this.affineTransform.setToTranslation(bound.getMinX(), bound.getMinY() + bound.getHeight());
			this.affineTransform.scale(1 + dx, 1 - dy);
			this.affineTransform.translate(-(bound.getMinX()), -(bound.getMinY() + bound.getHeight()));
			break;

		case x2y1:
			this.affineTransform.setToTranslation(bound.getMinX(), 0);
			this.affineTransform.scale(1 + dx, 1);
			this.affineTransform.translate(-(bound.getMinX()), 0);
			break;

		case x2y2:
			this.affineTransform.setToTranslation(bound.getMinX(), bound.getMinY());
			this.affineTransform.scale(1 + dx, 1 + dy);
			this.affineTransform.translate(-(bound.getMinX()), -(bound.getMinY()));
			break;
		default:
			break;
		}

		this.draw(graphics2d);
	}

	public void rotate(Graphics2D graphics2d, Point pStart, Point pEnd) {
		this.draw(graphics2d);
		double centerX = this.shape.getBounds().getCenterX();
		double centerY = this.shape.getBounds().getCenterY();

		double startAngle = Math.toDegrees(Math.atan2(centerX - pStart.x, centerY - pStart.y));
		double endAngle = Math.toDegrees(Math.atan2(centerX - pEnd.x, centerY - pEnd.y));
		double rotationAngle = startAngle - endAngle;

		if (rotationAngle < 0) {
			rotationAngle += 360;
		}
		this.affineTransform.rotate(Math.toRadians(rotationAngle), centerX, centerY);
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
	public abstract void setInitialPoint(int x, int y);

	public void setIntermediatePoint(int x, int y) {
	}

	public abstract void setFinalPoint(int x, int y);

	public abstract void movePoint(int x, int y);

}
