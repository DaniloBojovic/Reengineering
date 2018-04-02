package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class Circle
 * @author Danilo
 *
 */
public class Circle extends SurfaceShape {
	private Point center;
	private int radius;

	public Circle(){		
	}

	public Circle(Point center, int radius){
		this.center = center;
		this.radius = radius;		
	}
	/**
	 * Constructor that sets all parameters for circle
	 * @param center
	 * @param radius
	 * @param insideColor
	 * @param outsideColor
	 */
	public Circle(Point center, int radius, Color insideColor, Color outsideColor){
		this.center = center;
		this.radius = radius;
		setFillColor(insideColor);
		setColor(outsideColor);
	}

	public Circle(String cx, String cy, String r, Color color, Color outsideColor){
		Point c = new Point();
		c.setX(Integer.parseInt(cx));
		c.setY(Integer.parseInt(cy));
		setCenter(c);
		setRadius(Integer.parseInt(r));
		setColor(color);
		setFillColor(outsideColor);
	}
	/**
	 * Method used for selecting a circle
	 * @param g
	 */
	public void selected(Graphics g){
		new Line(new Point(center.getX(), center.getY()-radius), new Point(center.getX(), center.getY() + radius)).selected(g);
		new Line(new Point(center.getX()- radius, center.getY()), new Point(center.getX()+ radius, center.getY())).selected(g);
	}

	@Override
	public void fillMethod(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(getCenter().getX()- radius + 1, getCenter().getY() - radius + 1, radius * 2 - 2, 2 * radius - 2 );	
	}
	/**
	 *  Method used to check if circle contains point
	 */
	@Override
	public boolean contains(int x, int y) {
		Point newP = new Point(x, y);
		if(newP.distance(getCenter()) <= radius)
			return true;
		else
			return false;
	}

	public void drawShape(Graphics g){
		g.setColor(getFillColor());
		g.fillOval(getCenter().getX()- radius + 1, getCenter().getY() - radius + 1, radius * 2 - 2, 2 * radius - 2 );
		g.setColor(getColor());
		g.drawOval(getCenter().getX()- radius, getCenter().getY() - radius, radius*2, 2*radius );

		if(isSelected())
			selected(g);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String toString() {
		return "Circle: ((" + center.getX() + ", " + center.getY() + "), " + radius + ")" + " " +
				getFillColor().getRGB() + " " + getColor().getRGB();
	}
	/**
	 * Method that changes circle
	 */
	@Override
	public void changeShape(Shape shape) {
		Circle circle = (Circle) shape;

		setCenter(circle.getCenter());
		setRadius(circle.getRadius());
		setColor(circle.getColor());
		setFillColor(circle.getFillColor());
	}
	/**
	 * Methods used for copying a circle
	 */
	@Override
	public Shape copyShape() {
		return new Circle(getCenter(), getRadius(), getFillColor(), getColor());
	}
}
