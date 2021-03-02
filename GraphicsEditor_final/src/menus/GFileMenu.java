package menus;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import file.GFile;
import frames.GDrawingPanel;
import main.GConstants;

public class GFileMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;


	// association
	public void association(GDrawingPanel drawingpanel){
		this.drawingpanel = drawingpanel;
	}

	//working variables
	File directory;
	File file;

	public GFileMenu(String name) {
		super(name);
		
		for (GConstants.EFileMenu eMenu: GConstants.EFileMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
			menuItem.setActionCommand(eMenu.getActionCommand());
			menuItem.addActionListener(this.actionHandler);
			menuItem.setAccelerator(eMenu.getCommand()
					);
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
		
		
		this.directory = new File("./data");
		this.file = null;
	}

	public void initialize() {

	}
	
	public void imageOpen() {
		// Image Select
		JFileChooser fileChooser = new JFileChooser(this.directory);
		int returnValue = fileChooser.showOpenDialog(this.drawingpanel);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			this.drawingpanel.addImage(fileChooser.getSelectedFile());
		}
	}
	
	public void checkSave() {
		if(this.drawingpanel.isUpdated()) {
			int reply = JOptionPane.showConfirmDialog(this.drawingpanel,"변경내용을 저장할까요?","변경 확인",JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION) {
				this.save();
			}
		}
	}
	public void nnew() {
		this.checkSave();
		
		this.drawingpanel.clearShapes();
		this.file = null;
	}

	public void open(){
		this.checkSave();
		this.drawingpanel.clearShapes();
		
		JFileChooser fileChooser = new JFileChooser(this.directory);
		int returnValue = fileChooser.showOpenDialog(this.drawingpanel);
//		if(returnValue == JFileChooser.APPROVE_OPTION) {
//			this.drawingpanel.clearShapes();
//			this.directory = fileChooser.getCurrentDirectory();
//			this.file = fileChooser.getSelectedFile();
//			GFile gFile = new GFile();
//			Object shapes = gFile.readObject(this.file);
//			this.drawingpanel.setShapes(shapes);
//		}
	}
	public void save() {
		if(this.file==null) {
			this.saveAs();
		}else {
			GFile gFile = new GFile();
			gFile.saveObject(drawingpanel.getShapes(), this.file);
			this.drawingpanel.setUpdate(false);
		}
	}

	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser(this.directory);
		int returnValue = fileChooser.showOpenDialog(this.drawingpanel);
//		if(returnValue == JFileChooser.APPROVE_OPTION) {
//			this.directory = fileChooser.getCurrentDirectory();
//			this.file = fileChooser.getSelectedFile();
//			this.save();
//		}
	}
	public void print() {
		PrinterJob printerjob = PrinterJob.getPrinterJob();
		if (printerjob.printDialog()) {
			try {
				printerjob.print();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void exit() {
		this.checkSave();
		System.exit(0);
	}
}
