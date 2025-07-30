package main;

import java.awt.Dimension;
import java.awt.Point;

import shapeTools.GOval;
import shapeTools.GRectangle;
import shapeTools.GShape;

public class GConstants {

	public static class CFrame {
		public final static Point point = new Point(200, 300);
		public final static Dimension dimesion = new Dimension(400, 600);
	}

	public enum EDrawingState {
		e2PointDrawing,
		eNPointDrawing
	};

	public enum EShapeTool {
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GRectangle(), "Line"),
		ePolygon(new GRectangle(), "Polygon");

		private GShape GShapeTool;
		private String text;
		private EDrawingState eDrawingState;

		private EShapeTool(GShape GShapeTool, String text) {
			this.GShapeTool = GShapeTool;
			this.text = text;
			
		}
		public GShape getShapeTool() {
			return this.GShapeTool;
		}

		public String getText() {
			return this.text;
		}
		
	}
}
