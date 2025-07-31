package main;

import java.awt.Dimension;
import java.awt.Point;

import shapeTools.GOval;
import shapeTools.GPolygon;
import shapeTools.GRectangle;
import shapeTools.GShapeTool;

public class GConstants {

	public static class CFrame {
		public final static Point point = new Point(200, 300);
		public final static Dimension dimension = new Dimension(400, 600);
	}
	
	public static class CAnchor{
		public final static Dimension dimension = new Dimension(10,10);
		
		public static Point getPoint(double x, double y) {
			Point point = new Point();
			point.setLocation(x, y);
			
			return point;
		}
		
		public static Point getVertex(double x, double y) {
			Point point = new Point();
			point.setLocation(x-5, y-5);
			
			return point;
		}
		
		
		public static Point getRotatePoint(double x, double y) {
			Point point = new Point();
			point.setLocation(x, y-50);
			
			return point;
		}
	}

	public enum EDrawingState {
		e2PointDrawing,
		eNPointDrawing
	};

	public enum EShapeTool {
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GRectangle(), "Line"),
		ePolygon(new GPolygon(), "Polygon");

		private GShapeTool GShapeTool;
		private String text;
		private EDrawingState eDrawingState;

		private EShapeTool(GShapeTool GShapeTool, String text) {
			this.GShapeTool = GShapeTool;
			this.text = text;
			
		}

		public GShapeTool getShapeTool() {
			return this.GShapeTool;
		}

		public String getText() {
			return this.text;
		}
		
	}
	public enum EMenu{
		eFile("파일"),
		eEdit("편집"),
		eHelp("도움말");
		
		private String text;
		private EMenu(String text) {
			this.text = text;
			
		}
		public String getText() {
			return this.text;
		}
		
	}
	public enum EFileMenuItem{
		eNew("새로만들기"),
		eOpen("열기"),
		eSave("저장"),
		eSaveAs("다른이름으로"),
		ePrint("프린트"),
		eExit("나가기");
		
		private String text;
		private EFileMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
}
