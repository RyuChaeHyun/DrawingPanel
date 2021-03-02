package shape;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class GRectangle extends GShape implements Cloneable {
	
	int originX, originY;
	public GRectangle() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.shape = new Rectangle();
	}

	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
//		this.originX = x;
//		this.originY = y;
	}

	@Override
	public void setPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		int w = x - rectangle.x;
		int h = y - rectangle.y;
		rectangle.setSize(w,h);
//		this.shape = new Rectangle2D.Double();
	}

	

	@Override
	public void move(int dx, int dy) {
		Path2D rectangle = (Path2D) this.shape;

		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		rectangle.transform(at);
//		this.shape = at.createTransformedShape(this.shape);
//		Rectangle bound = this.shape.getBounds();
//		AffineTransform transform = new AffineTransform();
//		transform.rotate(Math.toRadians(1), bound.getCenterX(), bound.getCenterY());
//		this.shape = transform.createTransformedShape(this.shape);
	}
	
	@Override
	public void addPoint(int x, int y) {
	}
}
