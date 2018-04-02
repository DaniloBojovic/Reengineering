package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class SurfaceShape used for shapes that have inside and outside color
 * @author Danilo
 *
 */
public abstract class SurfaceShape extends Shape {

	private Color fillColor = Color.white;

	public SurfaceShape() {
	}
	/**
	 * Constructor used to set inside color
	 * @param fillColor
	 */
	public SurfaceShape(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color newFillColor) {
		this.fillColor = newFillColor;
	}

	public abstract void fillMethod(Graphics g);
}
