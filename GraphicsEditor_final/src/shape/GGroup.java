package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import main.GConstants;

public class GGroup extends GRectangle {
   private static final long serialVersionUID = GConstants.serialVersionUID;
   
   private Vector<GShape> gshapes;
   
   public GGroup() {
      this.gshapes = new Vector<GShape>();
         }
   
   public void draw( Graphics graphics ) {
      Rectangle unionRectangle = new Rectangle();
      for ( GShape gshape : this.gshapes ) {
         gshape.draw( graphics );
         unionRectangle.union( gshape.getBounds() );
      }
      this.shape = unionRectangle;
      if( this.bSelected ) {
         Graphics2D graphics2d = (Graphics2D) graphics;
         this.anchors.draw( graphics2d );
      }
   }
   
   public GShape clone() {
      try {
         return this.getClass().getDeclaredConstructor().newInstance();
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public void setLineColor( Color lineColor ) {
      for (GShape gshape : this.gshapes ) {
         gshape.setLineColor(lineColor );
      }
   }
   
   public void setFillColor( Color fillColor ) {
      for (GShape gshape : this.gshapes ) {
         gshape.setFillColor(fillColor );
      }
   }
   
   public boolean contains( int x, int y ) {
      boolean bContains = false;
      this.eSelectedAnchor = null;
      if( this.bSelected ) {
         this.eSelectedAnchor = this.anchors.contains( x, y );
      }
      if( this.eSelectedAnchor == null ) {
         for( GShape gshape : this.gshapes ) {
            if( gshape.contains( x, y ) ) {
               this.eSelectedAnchor = GAnchors.EAnchors.MM;
               bContains = true;
               break;
            }
         }
      } else {
         bContains = true;
      }
      return bContains;
   }
   
   public Rectangle getBounds() {
      Rectangle unionRectangle = new Rectangle();
      for ( GShape gshape : this.gshapes ) {
         unionRectangle.union( gshape.getBounds() );
      }
      this.shape = unionRectangle;
      return this.shape.getBounds();
   }

@Override
public void move(int dx, int dy) {
	
}

@Override
public void setOrigin(int x, int y) {
	
}

@Override
public void setPoint(int x, int y) {
	
}

@Override
public void addPoint(int x, int y) {
	
}

}