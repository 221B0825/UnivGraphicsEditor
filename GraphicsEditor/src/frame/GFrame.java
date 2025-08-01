package frame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import main.GConstants;
import menu.GMenuBar;

public class GFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;

	// components
	private GPanel panel; // 자식을 외부에서 보일 수 있도록 노출시킬 수 있음
	private GToolBar toolBar;
	private GMenuBar menuBar;
	private GWindowHandler windowHandler;

	// constructor
	public GFrame() {
		// 내부 속성 --> 웬만하면 변하지 않음
		// initialize attributes
		this.setLocation(GConstants.CFrame.point);
		this.setSize(GConstants.CFrame.dimesion);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// initialize components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);

		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);

		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);

		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);

		this.windowHandler = new GWindowHandler();
		this.addWindowListener(this.windowHandler);

		// set association
		this.menuBar.setAssciation(this.panel);
		this.toolBar.setAssciation(this.panel);
	}

	public void initialize() {
		this.toolBar.initialize();
		this.panel.initialize();

	}

	private class GWindowHandler extends WindowAdapter {
		private boolean bCancel;

		public GWindowHandler() {
			bCancel = false;
		}

		@Override
		public void windowClosing(WindowEvent e) {
//			저장할거냐고 물어보기
			this.bCancel = menuBar.getCheckSave();
			if(!bCancel) {
				System.exit(0);
			}
			

		}

	}

}
