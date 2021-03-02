package shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Line2D.Double;

import shape.GShape.EDrawingStyle;

public class GSketch extends GShape implements Cloneable{

	private static final long serialVersionUID = 1L;

	public GSketch() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.shape = new Path2D.Double();

	}

	@Override
	public void setOrigin(int x, int y) {
		Path2D sketch = (Path2D)shape;
		sketch.moveTo(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
	     Path2D sketch = (Path2D)shape;
	     sketch.lineTo(x, y);		
	}

	@Override
	public void addPoint(int x, int y) {

	}
	@Override
	public void move(int dx, int dy) {
		Path2D sketch = (Path2D) this.shape;
		AffineTransform at = new AffineTransform();
		at.translate(dx, dy);
		sketch.transform(at);
		
	}

}
