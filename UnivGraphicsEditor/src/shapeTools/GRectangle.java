package shapeTools;

import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EDrawingStyle;

public class GRectangle extends GShapeTool {
	// attributes
	private static final long serialVersionUID = 1L;

	// components

	// constructors
	public GRectangle() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Rectangle();
	}

	public Object clone() {
		GShapeTool cloned = (GShapeTool) super.clone(); //앵커를 붙인 걸 가져옴
		cloned.shape = (Shape) ((Rectangle)(this.shape)).clone(); //내가 가진 쉐잎에 렉탱글을 붙인 걸 변환해서 줌
		//실체가 없어서 클론을 할 수 없음 클론을 하려면 원래가 무슨 타입인지 알아야 함
		//shape에는 객체의 특정한 값만 끌어내기 위한 get 밖에 없음 거기서 shape이 클론이 안됨 
		return cloned;
	}

	// getters & setters

	// methods
	@Override
	public void setInitialPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);

	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x - rectangle.x, y - rectangle.y);

	}

}
