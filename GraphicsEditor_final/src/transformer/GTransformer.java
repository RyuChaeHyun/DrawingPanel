package transformer;

import java.awt.Graphics2D;

import shape.GShape;

public abstract class GTransformer {
	protected GShape shape;
	
	public GTransformer() {
		this.setShape(null);
	}
	public GShape getShape() {
		return shape;
	}
	public void setShape(GShape shape) {
		this.shape = shape;
	}
	public abstract void initTransforming(Graphics2D g2D, int x, int y);
	public abstract void keepTransforming(Graphics2D g2D, int x, int y);
	public abstract void finishTransforming(Graphics2D g2D, int x, int y);
	public void continueTransforming(Graphics2D g2D, int x, int y) {
	}
	public void pasteMove() {
		
	}
}
