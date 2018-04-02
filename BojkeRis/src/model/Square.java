package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class Square
 * @author Danilo
 *
 */
public class Square extends SurfaceShape {

	private Point topLeft;
	private int side;

	public Square(){		
	}

	public Square(Point topLeft, int side){	
		this.topLeft = topLeft;
		this.side = side;
	}
	/**
	 * Constructor that sets all parameters for square
	 * @param topLeft
	 * @param side
	 * @param insideColor
	 * @param outsideColor
	 */
	public Square(Point topLeft, int side, Color insideColor, Color outsideColor){
		this.topLeft = topLeft;
		this.side = side;
		setFillColor(insideColor);
		setColor(outsideColor);
	}

	public Square(String x, String y, String s, Color insideColor, Color outsideColor){
		Point upLeft = new Point();
		upLeft.setX(Integer.parseInt(x));
		upLeft.setY(Integer.parseInt(y));
		setTopLeft(upLeft);
		setSide(Integer.parseInt(s));
		setColor(outsideColor);
		setFillColor(insideColor);
	}

	public Line squareDiagonal(){
		return new Line(topLeft, new Point(topLeft.getX() + side, topLeft.getY() + side));
	}

	public Point squareCenter(){
		return squareDiagonal().lineMiddle();
	}
	/**
	 * Method used for selecting a square
	 * @param g
	 */
	public void selected(Graphics g){
		new Line(getTopLeft(), new Point(getTopLeft().getX()+ side, getTopLeft().getY())).selected(g);
		new Line(getTopLeft(), new Point(getTopLeft().getX(), getTopLeft().getY()+ side)).selected(g);
		new Line(new Point(getTopLeft().getX()+ side, getTopLeft().getY()), squareDiagonal().getEndPoint()).selected(g);
		new Line(new Point(getTopLeft().getX(), getTopLeft().getY()+ side), squareDiagonal().getEndPoint()).selected(g);
	}

	@Override
	public void fillMethod(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(getTopLeft().getX()+1, getTopLeft().getY()+1, getSide()-1, side-1);		
	}
	/**
	 * Method used to check if square contains point
	 */
	@Override
	public boolean contains(int x, int y) {
		if((topLeft.getX() < x && topLeft.getX() + side > x)    &&
				topLeft.getY() < y && topLeft.getY() + side > y)
			return true;
		else
			return false;		
	}

	public void drawShape(Graphics g){
		fillMethod(g);
		g.setColor(getColor());
		g.drawRect(getTopLeft().getX(), getTopLeft().getY(), getSide(), side);
		if(isSelected())
			selected(g);
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point upLeft) {
		this.topLeft = upLeft;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String toString() {
		return "Square: ((" + getTopLeft().getX() + ", " + getTopLeft().getY() + "), " +
				side + ")" + getFillColor().getRGB() + " " + getColor().getRGB();
	}
	/**
	 * Method that changes square
	 */
	@Override
	public void changeShape(Shape shape) {
		Square square = (Square) shape;
		
		setTopLeft(square.getTopLeft());
		setSide(square.getSide());
		setColor(square.getColor());
		setFillColor(square.getFillColor());
	}
	/**
	 * Methods used for copying a square
	 */
	@Override
	public Shape copyShape() {
		return new Square(getTopLeft(), getSide(), getFillColor(), getColor());
	}
}
