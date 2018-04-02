package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class Line
 * @author Danilo
 *
 */
public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;

	public Line(){		
	}

	public Line(Point startPoint, Point endPoint){		
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	/**
	 * Constructor that sets all parameters for line
	 * @param startPoint
	 * @param endPoint
	 * @param lineColor
	 */
	public Line(Point startPoint, Point endPoint, Color lineColor){	
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setColor(lineColor);
	}

	public Line(String startX, String startY, String endX, String endY, Color col){
		startPoint.setX(Integer.parseInt(startX));
		startPoint.setY(Integer.parseInt(startY));
		endPoint.setX(Integer.parseInt(endX));
		endPoint.setX(Integer.parseInt(endY));
		setColor(col);
	}

	public Point lineMiddle(){
		int middleX = ( getStartPoint().getX() + getEndPoint().getX() ) / 2;
		int middleY = ( startPoint.getY() + endPoint.getY() ) / 2;
		return new Point(middleX, middleY);
	}
	/**
	 * Method used for selecting a line
	 * @param g
	 */
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		lineMiddle().selected(g);
	}
	/**
	 *  Method used to check if line contains point
	 */
	public boolean contains(int x, int y){
		boolean contains = false;
		if ((startPoint.distance(new Point(x, y)) + endPoint.distance(new Point(x, y))) <= (this.lineLength() + 0.2))
			contains = true;
		return contains;
	}

	public void drawShape(Graphics g){
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

		if(isSelected()){
			selected(g);
		}
	}

	public double lineLength(){
		double length = startPoint.distance(endPoint);
		return length;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line ((" + startPoint.getX() + ", " + startPoint.getY() + "), (" + 
				endPoint.getX() + ", " + endPoint.getY() + "))" + " " + getColor().getRGB();
	}
	/**
	 * Method that changes line
	 */
	@Override
	public void changeShape(Shape shape) {
		Line line = (Line) shape;

		setStartPoint(line.getStartPoint());
		setEndPoint(line.getEndPoint());
		setColor(line.getColor());		
	}
	/**
	 * Methods used for copying a line
	 */
	@Override
	public Shape copyShape() {
		return new Line(startPoint, endPoint, getColor());
	}

}
