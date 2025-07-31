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
		public final static Dimension dimesion = new Dimension(400, 600);
	}
	
	public static class CAnchor{
		public final static int wAnchor = 10;
		public final static int hAnchor = 10;
	}

	public enum EDrawingStyle {
		e2PointDrawing,
		eNPointDrawing
	};
	public enum EDrawingState{
		eStop,
		eDrawing,
		eMove,
		eResize,
		eRotate,
		eShear
	};

	public enum EAnchors {	
		x0y0,
		x0y1, 
		x0y2,
		x1y0,
		x1y2,
		x2y0,
		x2y1,
		x2y2,
		RR;
	}

	public enum EShapeTool {
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GRectangle(), "Line"),
		ePolygon(new GPolygon(), "Polygon");

		private GShapeTool GShapeTool;
		private String text;

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
