package frames;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants;
import main.GConstants.ECursor;
import main.GConstants.EToolbar;
import menus.GColorMenu;
import shape.GAnchors;
import shape.GImageRectangle;
import shape.GShape;
import shape.GShape.EDrawingStyle;
import tool.DeepCloner;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private enum EDrawingState {
		eIdle, eDrawing, eTransforming;
	}
	private EDrawingState eDrawingState;	
	private Color lineColor, fillColor;
	private boolean bUpdated;
	private boolean bDrawing;
	
	// components
	private MouseHandler mouseHandler;
	private Vector<GShape> shapes;
	private Cpboard board;
	private DeepCloner deepCloner;

	// association components
	
	//working variables
	private GShape currentTool;
	private GShape currentShape;
	private GTransformer transformer;
	private GShape copyShape;
	
	// constructors and initializers
	public GDrawingPanel() {
		// attributes

		this.setBackground(Color.white);
		this.setForeground(Color.BLACK);
		this.eDrawingState = EDrawingState.eIdle;
		
		this.lineColor = null;
		this.fillColor = null;
		this.bUpdated = false;
		this.bDrawing = true;

		// components
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.shapes = new Vector<GShape>();

		this.deepCloner = new DeepCloner();
		this.board = new Cpboard();
		//working variables
		this.currentTool = null;
		this.currentShape = null;
		this.transformer = null;
	}

	public void initialize() {
		// set associations
		// set associative attributes
		// initialize components
		this.lineColor = this.getForeground();
//		this.fillColor = this.getBackground();
	}

	// setters & getters
	public Vector<GShape> getShapes() {
		return this.shapes;
	}
	public void setShapes(Object shapes) {
		this.shapes = (Vector<GShape>) shapes;
		this.repaint();
	}
	public void clearShapes() {
		this.shapes.clear();
		this.repaint();
	}
	public void setCurrentTool(EToolbar eToolBar) {
		this.currentTool = eToolBar.getTool();
	}
	public boolean isUpdated() {
		return this.bUpdated;
	}
	public void setUpdate(boolean bUpdated) {
		this.bUpdated = bUpdated;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	// methods
	public void paint(Graphics graphics) {
		super.paint(graphics);

		// 화면 확대 축소
//		Graphics2D graphics2D = (Graphics2D)graphics;
//		AffineTransform transform = new AffineTransform();
//		transform.scale(2, 2);
//		graphics2D.setTransform(transform);
		
		// user defined drawing
		for (GShape shape: this.shapes) {
			shape.draw(graphics);
		}		
	}

	private void checkCursor(int x, int y) {
		// 커서 만들어 적용하는 법
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Image Image = toolKit.getImage("data/cursor.jpg");
		Cursor mCursor = toolKit.createCustomCursor(Image, new Point (10,10), "mjuCursor");
		this.setCursor(mCursor);

		GShape selectedShape = this.onShape(x, y);
		if(selectedShape==null) {
			this.setCursor(ECursor.eDefault.getCursor());
		}else {
			GAnchors.EAnchors eSelectedAnchor = selectedShape.geteSelectedAnchor();
			switch(eSelectedAnchor) {
			case NW: this.setCursor(ECursor.eNW.getCursor()); break;
			case NN: this.setCursor(ECursor.eNN.getCursor()); break;
			case NE: this.setCursor(ECursor.eNE.getCursor()); break;
			case EE: this.setCursor(ECursor.eEE.getCursor()); break;
			case SE: this.setCursor(ECursor.eSE.getCursor()); break;
			case SS: this.setCursor(ECursor.eSS.getCursor()); break;
			case SW: this.setCursor(ECursor.eSW.getCursor()); break;
			case WW: this.setCursor(ECursor.eWW.getCursor()); break;
			case RR: this.setCursor(ECursor.eRotate.getCursor()); break;
			default: this.setCursor(mCursor); break;
			}
		}

	}

	private GShape onShape(int x, int y) {
		for(GShape shape : this.shapes) {
			if(shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}
	
	private void setSelected(GShape selectedShape) {
		for(GShape shape : this.shapes) {
			shape.setSelected(false);
		}
		selectedShape.setSelected(true);
		this.repaint();
	}
	private void initTransforming(GShape shape, int x, int y) {
		if(shape==null) {
			Graphics2D g2D = (Graphics2D) this.getGraphics();
			this.bDrawing = true;
			//drawing
			this.currentShape = this.currentTool.clone();
			this.currentShape.setLineColor(this.lineColor);
			this.currentShape.setFillColor(this.fillColor);
			this.transformer = new GDrawer();
			this.transformer.setShape(this.currentShape);
			this.transformer.initTransforming(g2D, x, y);
		}
		else {
			this.bDrawing=false;
			//moving resize
			this.currentShape = shape;
			switch(shape.geteSelectedAnchor()) {
			case MM:
				this.transformer = new GMover();
				break;
			case RR:
				this.transformer = new GRotator();
				break;
			default:
				this.transformer = new GResizer();
				break;
			}
			this.transformer.setShape(this.currentShape);
			Graphics2D g2D = (Graphics2D) this.getGraphics();
			this.transformer.initTransforming(g2D, x, y);

		}
		}
		
		
		//
	
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		float dash[] = {1,3f};
		g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 10, dash, 0));
		this.transformer.keepTransforming(g2D, x, y);
		repaint();
	}
	
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		this.transformer.finishTransforming(g2D, x, y);
		
		this.bUpdated = true;
		setSelected(this.currentShape);
		
		if(this.bDrawing) {
			this.shapes.add(this.currentShape);
			this.currentShape.toPath();
		}
	}


	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		this.transformer.continueTransforming(g2D, x, y);
	}

	// inner class
	class MouseHandler implements MouseMotionListener, MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount() == 1) {
				this.mouse1Clicked(event);
			} else if (event.getClickCount() == 2) {
				this.mouse2Clicked(event);
			}
		}

		// n point drawing
		private void mouse1Clicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			GShape shape = onShape(x, y);
			if(shape==null) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState ==EDrawingState.eIdle) {
					initTransforming(null,x, y);
					eDrawingState = EDrawingState.eDrawing;
				}
			}else {
				setSelected(shape);
			}
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState ==EDrawingState.eDrawing) {
				continueTransforming(x, y);
			}
		}

		private void mouse2Clicked(MouseEvent event) {
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				finishTransforming(x, y);
				eDrawingState = EDrawingState.eIdle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState==EDrawingState.eDrawing) {
				keepTransforming(x, y);
			}
			checkCursor(x,y);
		}


		// 2 point drawing
		@Override
		public void mousePressed(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			GShape shape = onShape(x,y);
			if(eDrawingState ==EDrawingState.eIdle) {
				if(shape == null) {
					if(currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {
						initTransforming(null,x, y);
						eDrawingState = EDrawingState.eDrawing;
					}
				}else {
					initTransforming(shape,x,y);
					eDrawingState = EDrawingState.eTransforming;
				}
			}
		}


		@Override
		public void mouseDragged(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			if(eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(x,y);
			}else if(eDrawingState == EDrawingState.eDrawing) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {
					keepTransforming(x, y);
				}
			}
		}
		@Override
		public void mouseReleased(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			if(eDrawingState==EDrawingState.eTransforming) {
				finishTransforming(x,y);
				eDrawingState = EDrawingState.eIdle;
			}else if(eDrawingState==EDrawingState.eDrawing) {
				if(currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {
					finishTransforming(x, y);
					eDrawingState = EDrawingState.eIdle;
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}
	}

	public void addImage(File imageFile) {
		this.shapes.add(new GImageRectangle(imageFile));
		this.repaint();
	}

	public void copy() {
		this.copyShape = (GShape) this.deepCloner.clone(this.currentShape);
	}

	public void cut() {
		this.copyShape = (GShape) this.deepCloner.clone(this.currentShape);
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = this.shapes.size() - 1; i >= 0; i--) { // index를 거꾸로 돌린다
			if (this.shapes.get(i).isbSelected()) {
				selectedShapes.add(this.shapes.get(i));
				this.shapes.remove(i);
				break;
			}
		}
		this.board.setContents(selectedShapes); // 저장은 set
		this.repaint();
	}
	
	public void paste() {
		this.shapes.add((GShape) this.deepCloner.clone(this.copyShape));
		this.transformer.pasteMove();
		this.repaint();
}
	
	public void delete() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = this.shapes.size() - 1; i >= 0; i--) { 
			if (this.shapes.get(i).isbSelected()) {
				this.shapes.remove(i);
				break;
			}
		}
		this.board.setContents(selectedShapes);
		this.repaint();
	}


}
