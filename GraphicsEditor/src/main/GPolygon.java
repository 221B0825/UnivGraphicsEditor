package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.lang.reflect.InvocationTargetException;

public class GPolygon {
	protected Polygon polygon;
	
	public GPolygon(){
		this.polygon = new Polygon();
	}
	
	public void initPoint(int x, int y) {
		this.polygon = new Polygon();
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);
		//polygon.addPoint(x, y);
		
	}
	
	public void addPoint(int x, int y) {
		Polygon polygon = this.polygon;
		polygon.addPoint(x, y);
	}
	
	public void setPoint(int x, int y) {
		Polygon polygon = this.polygon;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}

	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(this.polygon);
	}

}
