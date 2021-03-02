package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shape.GAnchors.EAnchors;



public class GResizer extends GTransformer {
	private Point2D.Double pre;
	
	public GResizer() {
		pre = new Point2D.Double();
	}

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.pre.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y) {
//	    this.getShape().draw(g2d);
//
//	      Point2D point = new Point2D.Double(x,y);
//	      AffineTransform affineTransform = new AffineTransform();
//	      affineTransform.translate(point.getX(), point.getY());
//	      
//	      Point2D resizePoint = this.getResizedPoint();
//	      affineTransform.translate(resizePoint.getX(), resizePoint.getY());
//
//	      Point2D computedPoint = this.computedPoint(this.pre, point);
//	      affineTransform.scale(computedPoint.getX(), computedPoint.getY());
//	      affineTransform.translate(-resizePoint.getX(), -resizePoint.getY());
//
//	      this.getShape().maketransformShape(affineTransform);
//	      this.getShape().draw(g2d);
//	      this.pre.setLocation(x,y);
		
		this.getShape().draw(g2d);
		
		Point2D p = new Point2D.Double(x,y);
		AffineTransform affineTransform = new AffineTransform();
		Point2D resizeOrigin = this.getResizedPoint();
		
		affineTransform.translate(resizeOrigin.getX(), resizeOrigin.getY());
		Point2D resizePoint = this.computedPoint(this.pre, p);

		affineTransform.scale(resizePoint.getX(), resizePoint.getY());
		affineTransform.translate(-resizeOrigin.getX(), -resizeOrigin.getY());

		this.getShape().maketransformShape(affineTransform);
		this.getShape().draw(g2d);
		this.pre.setLocation(x, y);
	}

	private Point2D computedPoint(Point2D previousP, Point2D currentP) {
		double nx = currentP.getX();
		double ny = currentP.getY();
		double px = previousP.getX();
		double py = previousP.getY();
		double dW = 0;
		double dH = 0;
		switch (this.getShape().geteSelectedAnchor()) {
		case EE:
			dW = nx - px;
			dH = 0;
			break;
		case WW:
			dW = -(nx - px);
			dH = 0;
			break;
		case NN:
			dW = 0;
			dH =-(ny - py);
			break;
		case NE:
			dW = nx - px;
			dH = -(ny - py);
			break;
		case NW:
			dW = -(nx - px);
			dH = -(ny - py);
			break;
		case SE:
			dW = nx - px;
			dH = ny - py;
			break;
		case SW:
			dW = -(nx - px);
			dH = ny - py;
			break;
		case SS:
			dW = 0;
			dH = ny-py;
			break;
		default:
			break;
		}
		
		double currentW = this.getShape().getWidth();
		double currentH = this.getShape().getHeight();
		
		double xFactor = 1.0;
		if (currentW > 0.0)
			xFactor = (1.0 + dW / currentW);
		double yFactor = 1.0;
		if (currentH > 0.0)
			yFactor = (1.0 + dH / currentH);
		return new Point.Double(xFactor, yFactor);
	}
	
	private Point getResizedPoint() {
		Point resizeAnchor = new Point();
		if (getShape().geteSelectedAnchor() == EAnchors.NW) {
			resizeAnchor.setLocation(
					getShape().getAnchorss().getAnchors().get(EAnchors.SE.ordinal()).getX(),
					getShape().getAnchorss().getAnchors().get(EAnchors.SE.ordinal()).getY());
		} else if (getShape().geteSelectedAnchor() == EAnchors.NN) {
			resizeAnchor.setLocation(
					0, 
					getShape().getAnchorss().getAnchors().get(EAnchors.SS.ordinal()).getY());
		}  else if (getShape().geteSelectedAnchor() == EAnchors.WW) {
			resizeAnchor.setLocation(
					getShape().getAnchorss().getAnchors().get(EAnchors.EE.ordinal()).getX(),
					0);
		} else if (getShape().geteSelectedAnchor() == EAnchors.SW) {
			resizeAnchor.setLocation(
					getShape().getAnchorss().getAnchors().get(EAnchors.NE.ordinal()).getX(),
					getShape().getAnchorss().getAnchors().get(EAnchors.NE.ordinal()).getY());
		} else if (getShape().geteSelectedAnchor() == EAnchors.EE) {
			resizeAnchor.setLocation(getShape().getAnchorss().getAnchors().get(EAnchors.WW.ordinal()).getX(),
					0);
		} else if (getShape().geteSelectedAnchor() == EAnchors.NE) {
			resizeAnchor.setLocation(
					getShape().getAnchorss().getAnchors().get(EAnchors.SW.ordinal()).getX(),
					getShape().getAnchorss().getAnchors().get(EAnchors.SW.ordinal()).getY());
		}else if (getShape().geteSelectedAnchor() == EAnchors.SS) {
			resizeAnchor.setLocation(
					0, 
					getShape().getAnchorss().getAnchors().get(EAnchors.NN.ordinal()).getY());
		} else if (getShape().geteSelectedAnchor() == EAnchors.SE) {
			resizeAnchor.setLocation(
					getShape().getAnchorss().getAnchors().get(EAnchors.NW.ordinal()).getX(),
					getShape().getAnchorss().getAnchors().get(EAnchors.NW.ordinal()).getY());
		}
		
		
		return resizeAnchor;
	}
	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
	}
}
