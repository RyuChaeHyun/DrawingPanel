package frames;

import java.util.Vector;

import shape.GShape;

public class Cpboard {

	private Vector<GShape> shapes;
	
	public Cpboard() {
		this.shapes = new Vector<GShape>();
	}
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();
		this.shapes.addAll(shapes);
	}
	
	public Vector<GShape> getContents() {
		Vector<GShape> clonedShapes = new Vector<GShape>();
		for (GShape shape : this.shapes) {
			GShape clonedShape = shape.clone();
			clonedShapes.add(clonedShape);
		}
		return clonedShapes;
	}

}
