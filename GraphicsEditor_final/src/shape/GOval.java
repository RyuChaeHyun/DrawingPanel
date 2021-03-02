package shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class GOval extends GShape implements Cloneable {

	private static final long serialVersionUID = 1L;


	public GOval() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.shape = new Ellipse2D.Float();
	}
	

	@Override
	public void setOrigin(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(x, y, 0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		int w = (int) (x - ellipse.getX());
		int h = (int) (y - ellipse.getY());
		ellipse.setFrame(ellipse.getX(),ellipse.getY(), w, h);
	}
	@Override
	public void addPoint(int x, int y) {
	}


	@Override
	public void move(int dx, int dy) {
		Path2D ellipse = (Path2D) this.shape;
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		ellipse.transform(at);
//		ellipse.setFrame(ellipse.getX()+dx,ellipse.getY()+dy,ellipse.getWidth(),ellipse.getHeight());
	}
}
