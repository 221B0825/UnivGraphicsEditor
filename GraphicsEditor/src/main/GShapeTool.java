package main;

import java.awt.Graphics2D;

abstract public class GShapeTool {

	protected int x0, y0, x1, y1; // 자식들이 볼 수 있도록, Rectangle, Oval이 봐야 함

	public GShapeTool() {
		// 실제로 그리는 방법은 얘의 하위 클래스는 네모툴이나 원툴이 알고 있음
		// 정의만 함
		// 하위 클래스가 실제로 이 함수의 바디를 가지고 있을 테니까 이름만 있는 오브젝트이며 목적이 new를 하는 오브젝트가 아니다

	}

	public void initCoordinate(int x, int y) {
		x0 = x;
		x1 = x0;
		
		y0 = y;
		y1 = y0;

	}

	// abstract 하기에는 polygon만 이 메서드가 필요해서 껍데기만 만들어두고 polygon에서 override함
	public void keepDrawing(Graphics2D graphics2d, int x, int y) {

	}

	public void initPoint(int x, int y) {
		
	}

	public void addPoint(int x, int y) {

	}

	abstract public void draw(Graphics2D graphics2d, int x, int y);

}
