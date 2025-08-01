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

	public enum EDrawingStyle {
		e2PointDrawing,
		eNPointDrawing
	};
	
	public final static int wAnchor = 10;
	public final static int hAnchor = 10;
	
	public enum EAction{
		eMove,
		eResize,
		eRotate,
		eShear
	}

	public enum EShapeTool {
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GRectangle(), "Line"),
		ePolygon(new GPolygon(), "Polygon");

		private GShapeTool GShapeTool;
		private String text;
		private EDrawingStyle eDrawingState;

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
	
	public enum EEditMenuItem{
		eUndo("되돌리기"),
		eRedo("재실행");
		
		private String text;
		
		private EEditMenuItem(String text) {
			this.text =  text;
		}
		public String getText() {
			return this.text;
		}
	}
}
