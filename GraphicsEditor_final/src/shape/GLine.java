package shape;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class GLine extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;

	public GLine() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.shape = new Line2D.Float();
	}
	
	@Override
	public boolean contains(int x, int y) {
		boolean bContains = false;
		this.eSelectedAnchor = null;
		
		Path2D path = (Path2D)shape;
		Rectangle rect = path.getBounds();
		Point2D currentP = path.getCurrentPoint();
		Line2D line = new Line2D.Double();
		
		line.setLine(0,0,currentP.getX(), currentP.getY());
		if(rect.getX()==currentP.getX()) {
			line.setLine(rect.x+rect.width, 0, line.getX2(), line.getY2());
		}
		else {
			line.setLine(rect.x, 0, line.getX2(), line.getY2());
		}
		
		if(rect.getY()==currentP.getY()) {
			line.setLine(line.getX1(), rect.y+rect.height, line.getX2(), line.getY2());
		}
		else {
			line.setLine(line.getX1(), rect.y, line.getX2(), line.getY2());
		}
		
		if(this.bSelected) {
			this.eSelectedAnchor = this.anchors.contains(x, y);
		}
		
		if(this.eSelectedAnchor == null) {
			if(line.ptLineDist(x,y)<5&&line.getBounds().contains(x,y)) {
				bContains = true;
				this.eSelectedAnchor = GAnchors.EAnchors.MM;
			}
		}
		else {
			bContains = true;
		}
		return bContains;
	}
	@Override
	public void setOrigin(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(x, y, x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		Line2D line = (Line2D) this.shape;
		line.setLine(line.getX1(), line.getY1(), x, y);
	}

	@Override
	public void addPoint(int x, int y) {
	}

	@Override
	public void move(int dx, int dy) {
		Path2D line = (Path2D) this.shape;
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		line.transform(at);
//		line.setLine(line.get+dx, line.getY1()+dy,
//				line.getX2()+dx, line.getY2()+dy);
	}
}
