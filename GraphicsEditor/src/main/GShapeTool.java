package main;

import java.awt.Graphics2D;

abstract public class GShapeTool {
	
	protected int x0,y0,x1,y1;
	public GShapeTool() {
		
	}
	public void initCoordinate(int x, int y) {
		x0 = x;
		y0 = y;
		x1 = x;
		y1 = y;
	}
	

	public void setIntermediate(int x, int y) {
		//폴리곤 중간 점 찍기
	}
	public void keepDrawing(Graphics2D graphics2d, int x, int y) {
		//폴리곤 중간 점 찍을 때 계속 그리기
	}
	public void initPoint(int x, int y) {
		//폴리곤 처음 점 찍고 polygon 새로 생성하기
	}
	abstract public void draw(Graphics2D graphics2d, int x, int y);

}
