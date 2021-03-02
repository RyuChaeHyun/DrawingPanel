package frames;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.GConstants;

public class GMainFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		super();
		
		// initialize attributes
		this.setSize(GConstants.EMainFrame.eWidth.getValue(), 
				GConstants.EMainFrame.eHeight.getValue());
		this.setTitle("±×¸²ÆÇ");
		try {
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		// create,register components
		WindowActionHandler windowActionHandler = new WindowActionHandler();
		this.addWindowListener(windowActionHandler);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// set associations
		this.toolBar.setAssociation(this.drawingPanel);	
		this.menuBar.setAssociation(this.drawingPanel);	
		// initialize associative attributes
		
		// initialize components
		this.menuBar.initialize();
		this.toolBar.initialize();		
		this.drawingPanel.initialize();	
	}
	public class WindowActionHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			menuBar.getFileMenu().checkSave();
		}
	}
}