package main;
import java.awt.BorderLayout;


import javax.swing.JFrame;

public class GFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private GPanel panel; // 자식을 외부에서 보일 수 있도록 노출시킬 수 있음
	private GToolBar toolBar;
	//constructor
	public GFrame() {
		//내부 속성 --> 웬만하면 변하지 않음
		// initialize attributes
		this.setLocation(GConstants.CGFrame.point);
		this.setSize(GConstants.CGFrame.dimesion);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize components
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		
		//set association
		this.toolBar.setAssciation(this.panel);
	}

}
