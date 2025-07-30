package main;
import java.awt.Dimension;
import java.awt.Point;

public class GConstants {

	public static class CGFrame{
		public final static Point point = new Point(200,300);
		public final static Dimension dimesion = new Dimension(400,600);
	}
	
	public static class CGToolBar{
		public final static String rectButton = "Rectangle";
		public final static String ovalButton = "Oval";
		public final static String lineButton = "Line";
		public final static String polygonButton = "Polygon";
		public final static Dimension dimesion = new Dimension(400,600);
	
	}
	
	public static class CDrawingState{
		public final static String drawing = "drawing";
		public final static String stop = "stop";
	}
}
