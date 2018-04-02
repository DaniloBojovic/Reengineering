package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class Point 
 * @author Danilo
 *
 */
public class Point extends Shape {
	private int x;
	private int y;

	public Point(){
	}

	public Point(int x, int y){
		this.x = x;
		this.y = y;	
	}
	/**
	 * Constructor that sets all parameters for point
	 * @param x
	 * @param y
	 * @param pointColor
	 */
	public Point(int x, int y, Color pointColor){
		this.x = x;
		this.y = y;
		setColor(pointColor); 
	}

	public Point(String xx, String yy, Color col){
		x = Integer.parseInt(xx);
		y = Integer.parseInt(yy);
		setColor(col);  	
	}
	/**
	 * Method used for selecting a point
	 * @param g
	 */
	public void selected(Graphics g){
		g.setColor(Color.blue);
		g.drawRect(x-3, y-3, 6, 6);
	}
	/**
	 * Method used to check if point is selected
	 */
	public boolean contains(int x, int y){
		boolean contains = false;
		if(this.distance(new Point(x, y)) <= 7)
			contains = true;
		return contains;
	}

	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);		

		if(isSelected()){
			selected(g);
		}
	}

	public double distance(Point p){
		int dx = x - p.getX();
		int dy = y - p.y;
		double d = Math.sqrt(dx*dx+dy*dy);
		return d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "Point (" + getX() + ", " + getY() + ")" + " " + getColor().getRGB();
	}
	/**
	 * Method that changes point
	 */
	@Override
	public void changeShape(Shape shape) {
		Point point = (Point) shape;
        
        this.setX(point.getX());
        this.setY(point.getY());
        this.setColor(point.getColor());
	}
	/**
	 * Methods used for copying a point
	 */
	@Override
	public Shape copyShape() {
		return new Point(getX(), getY(), getColor());
	}
}
