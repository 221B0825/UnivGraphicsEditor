package frame;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import main.GConstants;
import menu.GMenuBar;

public class GFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private GPanel panel; // 자식을 외부에서 보일 수 있도록 노출시킬 수 있음
	private GToolBar toolBar;
	private GMenuBar menuBar;
	//constructor
	public GFrame() {
		//내부 속성 --> 웬만하면 변하지 않음
		// initialize attributes
		this.setLocation(GConstants.CFrame.point);
		this.setSize(GConstants.CFrame.dimesion);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		// initialize components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
		
		//set association
		this.menuBar.setAssciation(this.panel);
		this.toolBar.setAssciation(this.panel);
	}
	public void initialize() {
		this.toolBar.initialize();
		this.panel.initialize();
		
	}

}
