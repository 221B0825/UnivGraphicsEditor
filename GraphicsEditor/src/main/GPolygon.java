package main;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class GPolygon extends GShapeTool {
	private Polygon polygon;

	public GPolygon() {
		this.polygon = new Polygon();
	}

	@Override
	public void initPoint(int x, int y) {
		// 첫 점이 찍힐 때 새로운 polygon이어야 함
		this.polygon = new Polygon();
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);

	}

	@Override
	public void keepDrawing(Graphics2D graphics2d, int x, int y) {
		// mouseMoved일때 계속 그리고 지우기 반복하기
		this.draw(graphics2d, x, y);
		Polygon polygon = this.polygon;
		polygon.xpoints[polygon.npoints - 1] = x;
		polygon.ypoints[polygon.npoints - 1] = y;
		this.draw(graphics2d, x, y);
	}

	@Override
	public void setIntermediate(int x, int y) {
		// mouseClicked일때 점 저장하기
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);
	}

	@Override
	public void draw(Graphics2D graphics2d, int x, int y) {
		graphics2d.draw(this.polygon);
	}

}
