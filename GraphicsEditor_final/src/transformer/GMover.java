package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shape.GShape;

public class GMover extends GTransformer {

	private Point2D pre;
	
	public GMover() {
		this.pre = new Point2D.Double();
	}
	
	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().initTransforming(x, y);
		this.pre.setLocation(x,y);
	}
	
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().draw(g2D);
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(x-this.pre.getX(), y-this.pre.getY());
		this.getShape().maketransformShape(affineTransform);
		
		this.shape.draw(g2D);
		this.pre.setLocation(x,y);

	}
	
	public void finishTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().finishTransforming(x, y);
	}
	
	@Override
	public void pasteMove() {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 10);
		this.shape.maketransformShape(affineTransform);
	}
}
