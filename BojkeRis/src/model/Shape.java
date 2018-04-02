
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
/**
 * Class shape that implements Serializable interface
 * @author Danilo
 *
 */
public abstract class Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1208360179002785529L;
	private Color color = Color.black;
	private boolean selected;

	public Shape() {   	
	}

	public Shape(Color color ) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color newColor) {
		this.color = newColor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * Abstract method for drawing a shape
	 * @param g
	 */
	public abstract void drawShape(Graphics g);
	/**
	 * Abstract method to check if shape contains point
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract boolean contains(int x, int y);
	/**
	 * Abstract method used to change shape
	 * @param shape
	 */
	public abstract void changeShape(Shape shape);
	/**
	 * Abstract method for copying a shape
	 * @return
	 */
	public abstract Shape copyShape();

	public void fillMethod(Graphics g){   	
	}
}
