package menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.GConstants;
import main.GConstants.EColorMenu;

public class GColorMenu extends GMenu {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;

	// components

	private int stroke;
	
	public GColorMenu(String name) {
		super(name);
		this.stroke = 0;
		for (GConstants.EColorMenu eMenu: GConstants.EColorMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
			menuItem.addActionListener(this.actionHandler);
			menuItem.setActionCommand(eMenu.getActionCommand());
			menuItem.setAccelerator(eMenu.getCommand());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}

	public void initialize() {
	}
	public void setLineColor() {
		Color lineColor = JColorChooser.showDialog(this.drawingpanel,
				EColorMenu.eLineColor.getTitle(), this.drawingpanel.getForeground());
		this.drawingpanel.setLineColor(lineColor);
	}
	public void setFillColor() {
		Color fillColor = JColorChooser.showDialog(this.drawingpanel,
				EColorMenu.eFillColor.getTitle(), this.drawingpanel.getForeground());
		this.drawingpanel.setFillColor(fillColor);
	}
	
	public int getStroke() {
		return stroke;
	}

	public void setStroke() {
		String []stroke = {"작게", "보통", "굵게", "더 굵게"};
		Graphics2D graphics2D = (Graphics2D)this.getGraphics();
		Object selected = JOptionPane.showInputDialog(null, "선 굵기를 선택하세요", "Stroke",
		  JOptionPane.QUESTION_MESSAGE, null, stroke, stroke[0]);
		if(((String)stroke[0]).equals(selected)) {
			this.stroke = 1;
			repaint();
		}
		else if(((String)stroke[1]).equals(selected)) {
			this.stroke = 2;
			repaint();
//			graphics2D.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0));
		}
		else if(((String)stroke[2]).equals(selected)) {
			this.stroke = 3;
			repaint();
//			graphics2D.setStroke(new BasicStroke(10,BasicStroke.CAP_ROUND,0));
		}
		else if(((String)stroke[3]).equals(selected)) {
			this.stroke = 4;
			repaint();
//			graphics2D.setStroke(new BasicStroke(20,BasicStroke.CAP_ROUND,0));
		}
	}
}
