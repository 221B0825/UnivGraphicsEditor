package main;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;

import shapeTools.GOval;
import shapeTools.GPolygon;
import shapeTools.GRectangle;
import shapeTools.GLine;
import shapeTools.GShapeTool;


public class GConstants {

	public static class CFrame {
		public final static Point point = new Point(200, 300);
		public final static Dimension dimesion = new Dimension(400, 600);
		public final static String title = "GraphicsEditor";
	}

	public enum EDrawingState {
		e2PointDrawing,
		eNPointDrawing
	};

	public enum EShapeTool {
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
}
