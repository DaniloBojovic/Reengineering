package model;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Class Rectangle
 * @author Danilo
 *
 */
public class Rectangle extends Square {
	private int rectHeight;

	public Rectangle(){		
	}

	public Rectangle(Point topLeft, int side, int rectHeight){
		super(topLeft, side);
		this.rectHeight = rectHeight;		
	}
	/**
	 * Constructor that sets all parameters for rectangle
	 * @param upLeft
	 * @param side
	 * @param rectHeight
	 * @param insideColor
	 * @param outsideColor
	 */
	public Rectangle(Point upLeft, int side, int rectHeight, Color insideColor, Color outsideColor){
		super(upLeft, side, insideColor, outsideColor);
		this.rectHeight = rectHeight;		
	}

	public Rectangle(String x, String y, String s, String w, Color insColor, Color outColor){
		Point upLeft = new Point();
		upLeft.setX(Integer.parseInt(x));
		upLeft.setY(Integer.parseInt(y));
		setTopLeft(upLeft);
		setSide(Integer.parseInt(s));
		setRectHeight(Integer.parseInt(w));
		setColor(outColor);
		setFillColor(insColor);
	}
	/**
	 * Method used for selecting a square
	 */
	public void selected(Graphics g){
		new Line(getTopLeft(), new Point(getTopLeft().getX()+ getSide(), getTopLeft().getY())).selected(g);
		new Line(getTopLeft(), new Point(getTopLeft().getX(), getTopLeft().getY()+ rectHeight)).selected(g);
		new Line(new Point(getTopLeft().getX()+ getSide(), getTopLeft().getY()), dijagonal().getEndPoint()).selected(g);
		new Line(new Point(getTopLeft().getX(), getTopLeft().getY()+ rectHeight), dijagonal().getEndPoint()).selected(g);
	}

	public void fillMethod(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(getTopLeft().getX() + 1, getTopLeft().getY() + 1, getSide() - 1, rectHeight - 1);
	}
	/**
	 * Method used to check if rectangle contains point
	 */
	public boolean contains(int x, int y) {
		if( (x > getTopLeft().getX() && x < getTopLeft().getX() + getSide()) &&
				(y > getTopLeft().getY() && y < getTopLeft().getY() + rectHeight)){
			return true;
		}
		else
			return false;
	}

	public void drawShape(Graphics g){
		fillMethod(g);
		g.setColor(getColor());
		g.drawRect(getTopLeft().getX(), getTopLeft().getY(), getSide(), rectHeight);

		if(isSelected()){
			selected(g);
		}
	}

	public Line dijagonal(){
		int xDownRight = getTopLeft().getX() + getSide();
		int yDownRight = getTopLeft().getY() + rectHeight;
		Point downRight = new Point(xDownRight ,yDownRight);
		Line d = new Line(getTopLeft(), downRight);
		return d;
	}

	public Point rectCenter(){
		return dijagonal().lineMiddle();
	}

	public int getRectHeight() {
		return rectHeight;
	}

	public void setRectHeight(int rectHeight) {
		this.rectHeight = rectHeight;
	}

	public String toString() {
		return "Rectangle: ((" + getTopLeft().getX() + ", " + getTopLeft().getY() + "), " + getSide() + 
				", " + getRectHeight() + ")" + getFillColor().getRGB() + " " + getColor().getRGB();
	}
}
