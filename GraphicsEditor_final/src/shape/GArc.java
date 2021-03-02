package shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Line2D.Double;

import shape.GShape.EDrawingStyle;

public class GArc extends GShape implements Cloneable{

	private Arc2D arc;
	private int x,y;
	
	private static final long serialVersionUID = 1L;
	public GArc() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.shape = new Arc2D.Double();
		this.arc = (Arc2D) this.shape;
	}
	

	@Override
	public void setOrigin(int x, int y) {
		this.arc.setFrame(x, y, 0, 0);
		this.arc.setArcType(Arc2D.PIE);
		this.arc.setAngleStart(0);
		this.arc.setAngleExtent(90);

	}

	@Override
	public void setPoint(int x, int y) {
		this.arc.setFrame(this.arc.getX(), this.arc.getY(), x-this.arc.getX(), (y-this.arc.getY())*2);
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub

	}
	@Override
	public void move(int dx, int dy) {
		Path2D arc = (Path2D) this.shape;
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		arc.transform(at);

	}
}
