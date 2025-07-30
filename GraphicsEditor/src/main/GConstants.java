package main;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;

public class GConstants {

	public static class CFrame{
		public final static Point point = new Point(200,300);
		public final static Dimension dimesion = new Dimension(400,600);
	}
	
	public enum EShapeTool { //원래 static이기 때문에 안써줘도 됨
		// 실제로 어레이임
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GLine(), "Line"),
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
	public static class CDrawingState{
		public final static String drawing = "drawing";
		public final static String stop = "stop";
	}
}
