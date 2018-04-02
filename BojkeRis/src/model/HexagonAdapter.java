package model;

import java.awt.Color;
import java.awt.Graphics;

import reinzenjering.Hexagon;
/**
 * HexagonAdapter class used for adapter pattern
 * @author Danilo
 *
 */
public class HexagonAdapter extends SurfaceShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3280765401954243871L;
	private Hexagon hexagon; 
	
	public HexagonAdapter(int x, int y, int r){
		hexagon = new Hexagon(x, y, r);
	}
	
	public HexagonAdapter(int x, int y, int r, Color insideColor, Color outsideColor){
		this(x, y, r);
		setFillColor(insideColor);
		setColor(outsideColor);
	}

	public HexagonAdapter(String x, String y, String r){
		hexagon = new Hexagon(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(r));
	}
	/** Method that draws a Hexagon by calling a draw method from Hexagon class
	 * 
	 */
	public void drawShape(Graphics g){	
		hexagon.setAreaColor(getFillColor());
		hexagon.setBorderColor(getColor());
		hexagon.paint(g);
		
		if(isSelected()){
			selected(g);
		}
	}

	public void fillMethod(Graphics g){
		g.setColor(getFillColor());
	}

	public void selected(Graphics g){
		int a = (int)(hexagon.getR()/2*Math.sqrt(3));
		Point[] points = new Point[]{
				new Point(hexagon.getX()+((hexagon.getR()/2)), hexagon.getY()- a),
				new Point(hexagon.getX()+hexagon.getR(), hexagon.getY()),
				new Point(hexagon.getX()+((hexagon.getR()/2)), hexagon.getY()+ a),
				new Point(hexagon.getX()-((hexagon.getR()/2)), hexagon.getY()+ a),
				new Point(hexagon.getX()-hexagon.getR(), hexagon.getY()),
				new Point(hexagon.getX()-((hexagon.getR()/2)),hexagon.getY()- a)
		};
		for(Point p: points){
			p.selected(g);
		}
	}

	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}

	public int getR() {
		return hexagon.getR();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public void setR(int r) {
		hexagon.setR(r);
	}

	public String toString() {
		return "Hexagon: ((" + hexagon.getX() + ", " + hexagon.getY() + "), " + hexagon.getR() + ")" +
				getFillColor().getRGB() + " " + getColor().getRGB();
	}

	@Override
	public void changeShape(Shape shape) {
		HexagonAdapter hexagonAdapter = (HexagonAdapter) shape;
		
		setX(hexagonAdapter.getX());
		setY(hexagonAdapter.getY());
		setR(hexagonAdapter.getR());
		setColor(hexagonAdapter.getColor());
		setFillColor(hexagonAdapter.getFillColor());
	}

	@Override
	public Shape copyShape() {
		return new HexagonAdapter(getX(), getY(), getR(), getFillColor(), getColor());
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hexagon.doesContain(x, y);
	}


}
