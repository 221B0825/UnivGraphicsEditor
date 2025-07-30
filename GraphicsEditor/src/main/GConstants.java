package main;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GConstants {

	public static class CGFrame{
		public final static Point point = new Point(200,300);
		public final static Dimension dimesion = new Dimension(400,600);
	}
	
	public enum EButton { //원래 static이기 때문에 안써줘도 됨
		// 실제로 어레이임
		eRectangle(new GRectangle(), "Rectangle",new ImageIcon("image/rectangle.png"),new ImageIcon("image/PressedRectangle.png")), // icon 추가
		eOval(new GOval(), "Oval",new ImageIcon("image/oval.png"),new ImageIcon("image/pressedOval.png")),
		eLine(new GLine(), "Line",new ImageIcon("image/line.png"),new ImageIcon("image/PressedLine.png")),
		ePolygon(new GPolygon(), "Polygon",new ImageIcon("image/polygon.png"),new ImageIcon("image/pressedPolygon.png"));
		
		private GShapeTool GShapeTool;
		private String text;
		private ImageIcon icon;
		private ImageIcon pressedIcon;
		private EButton(GShapeTool GShapeTool, String text, ImageIcon icon, ImageIcon pressedIcon) {
			this.GShapeTool = GShapeTool;
			this.text = text;
			this.icon = icon;
			this.pressedIcon = pressedIcon;
		}
		public GShapeTool getShapeTool() {
			return this.GShapeTool;
		}
		public String getText() {
			return this.text;
		}
		public ImageIcon getIcon() {
			return this.icon;
		}
		public ImageIcon getPressedIcon() {
			return this.pressedIcon;
		}
	}
	public static class CDrawingState{
		public final static String drawing = "drawing";
		public final static String stop = "stop";
	}
}
