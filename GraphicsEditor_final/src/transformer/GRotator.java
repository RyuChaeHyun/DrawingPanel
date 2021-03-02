package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shape.GShape;

public class GRotator extends GTransformer {

	private AffineTransform affineTransform;
	private Point2D.Double pre, center;
	private double centerX, centerY, startX, startY, endX, endY;

	
	public GRotator() {
		this.affineTransform = new AffineTransform();
		this.center = new Point2D.Double();
		this.pre = new Point2D.Double();
		
	}
	
	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.centerX = this.getShape().getCenterX();
		this.centerY = this.getShape().getCenterY();
		this.startX = x;
		this.startY = y;
		this.center.setLocation(this.centerX,this.centerY);
		this.pre.setLocation(x,y);
	}
	
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().draw(g2D);
		double startAngle = Math.toDegrees(Math.atan2(center.getX()-pre.getX(), center.getY()-pre.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX()-x, center.getY()-y));
		double rotationAngle = startAngle - endAngle;
		if(rotationAngle<0)
			rotationAngle+=360;
		this.affineTransform.setToRotation(Math.toRadians(rotationAngle), center.getX(), center.getY());

		this.getShape().setShape(this.affineTransform.createTransformedShape(this.shape.getShape()));
		this.getShape().draw(g2D);
		this.pre.setLocation(x,y);
		
	}
	
	public void finishTransforming(Graphics2D g2D, int x, int y) {

	}


}
