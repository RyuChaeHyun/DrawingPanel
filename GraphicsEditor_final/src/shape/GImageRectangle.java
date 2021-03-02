package shape;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GImageRectangle extends GShape implements Cloneable { // extends GRectangle·Î ¹Ù²ã¾ß ÇÔ

	// Attribute
	private BufferedImage image;
	
	// Constructor
	public GImageRectangle(File imageFile) {
		// Set Attribute
		this.eDrawingStyle = EDrawingStyle.e2Points;
		try {this.image = ImageIO.read(imageFile);} catch (IOException e) {e.printStackTrace();}
		
		// Create Component
		this.shape = new Rectangle();
		
		// Set Rectangle
		this.setOrigin(0, 0);
		this.setPoint(this.image.getWidth(), this.image.getHeight());
	}

	@Override
	public void draw(Graphics graphics) {
		// Draw Image
		Rectangle bound = this.shape.getBounds();
		graphics.drawImage(this.image, bound.x, bound.y, bound.width, bound.height, null);
		
		// Draw Anchor
		super.draw(graphics);
	}
	
	@Override
	public void setOrigin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		int w = x - rectangle.x;
		int h = y - rectangle.y;
		rectangle.setSize(w,h);
	}

	@Override public void addPoint(int x, int y) {}

	@Override
	public void move(int dx, int dy) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.translate(dx, dy);
	}
}
