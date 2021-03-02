package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import menus.GColorMenu;

public abstract class GShape implements Serializable {

	private static final long serialVersionUID = 1L;
	private int  tMoveX, tMoveY;
	protected Shape shape;
	private Color lineColor,fillColor;
	protected GAnchors.EAnchors eSelectedAnchor;
	
	protected GAnchors anchors;
	
	
	public enum EDrawingStyle {
		e2Points, eNPoints
	}
	
	protected EDrawingStyle eDrawingStyle;
	protected boolean bSelected;
	private GColorMenu colorMenu;
	
	public GShape() {
		this.lineColor=null;
		this.fillColor=null;
		this.bSelected=false;
		this.anchors = new GAnchors();
		this.eSelectedAnchor = null;
		this.colorMenu = new GColorMenu("ÄÃ·¯");
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public EDrawingStyle getEDrawingStyle() { 
		return this.eDrawingStyle;
		}

	public boolean isbSelected() {
		return bSelected;
	}
	public void setSelected(boolean bSelected) {
		this.bSelected=bSelected;
		if(this.bSelected) {
			this.anchors.setBounds(this.shape.getBounds());
		}
	}
	
	public GAnchors.EAnchors geteSelectedAnchor(){
		return this.eSelectedAnchor;
	}
	
	public GAnchors getAnchorss() {
		return anchors;
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		if(this.fillColor!=null) {
			graphics2d.setColor(this.fillColor);
			graphics2d.fill(this.shape);
		}
		if(this.lineColor != null) {
			graphics2d.setColor(this.lineColor);
			if(this.colorMenu.getStroke()==1) {
				graphics2d.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,0));
			}
			else if(this.colorMenu.getStroke()==2) {
				graphics2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0));
			}
			else if(this.colorMenu.getStroke()==3) {
				graphics2d.setStroke(new BasicStroke(10,BasicStroke.CAP_ROUND,0));
			}
			else if(this.colorMenu.getStroke()==4) {
				graphics2d.setStroke(new BasicStroke(20,BasicStroke.CAP_ROUND,0));
			}
			graphics2d.draw(this.shape);
		}
		if(this.bSelected) {
			this.anchors.draw(graphics2d);
		}
	}

	public GShape clone() {
		try {
			return this.getClass().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public void setFillColor(Color fileColor) {
		this.fillColor=fileColor;
	}
	
	public void maketransformShape(AffineTransform affineTransform) {
		this.shape = affineTransform.createTransformedShape(this.shape);
	}
	public boolean contains(int x, int y) {
		boolean bContains = false;
		if(this.bSelected) {
			this.eSelectedAnchor = this.anchors.contains(x,y);
		}
		if(this.eSelectedAnchor == null) {
			if(this.shape.contains(new Point(x, y))) {
				this.eSelectedAnchor = GAnchors.EAnchors.MM;
				bContains = true;
			}
		}
		else {
			bContains = true;
		}
		return bContains;
	}
	
	public Rectangle getBounds() {;
		return this.shape.getBounds();
	}
	
	public void initTransforming(int x, int y) {
		this.tMoveX = x;
		this.tMoveY = y;
	}
	public void keepTransforming(int x, int y) {
		this.move(x-this.tMoveX,y-this.tMoveY);
		this.tMoveX = x;
		this.tMoveY = y;
	}
	public void finishTransforming(int x, int y) {
		this.tMoveX = x;
		this.tMoveY = y;
	}
	
	public abstract void move(int dx, int dy);
	public abstract void setOrigin(int x, int y);
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	
	public double getCenterX() {
		return this.shape.getBounds2D().getCenterX();
	}

	public double getCenterY() {
		return this.shape.getBounds2D().getCenterY();
	}

	public double getWidth() {
		return this.shape.getBounds2D().getWidth();
	}

	public double getHeight() {
		return this.shape.getBounds2D().getHeight();
	}

	public void toPath() {
		AffineTransform at = new AffineTransform();
		this.shape = at.createTransformedShape(this.shape);
	}
	
}
