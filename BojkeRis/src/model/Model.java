package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Model used for MVC pattern
 * @author Danilo
 *
 */
public class Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> selection = new ArrayList<Shape>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public ArrayList<Shape> getSelection() {
		return selection;
	}

	public void setSelection(ArrayList<Shape> selection) {
		this.selection = selection;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	/**
	 * Method that adds shape
	 * @param shape
	 */
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public void addForFrontBack(Shape element, int index){
		shapes.add(index, element);
	}
	/**
	 * Method that removes shape at specific index
	 * @param index
	 * @return
	 */
	public boolean remove(Shape shape) {
		selection.remove(shape);
		return shapes.remove(shape);
	}
	/**
	 * Method that removes shape at specific index
	 * @param index
	 * @return
	 */
	public Shape remove(int index){
		return shapes.remove(index);
	}
	/**
	 * Method that add selected shapes
	 * @param shape
	 */
	public void addToSelection(Shape shape) {		
		selection.add(shape);
		shape.setSelected(true);
	}
	/**
	 * Method that select shapes at certain index
	 * @param index
	 * @return
	 */
	public Shape getSelectedShape(int index) {
		return selection.get(index);
	}
	/**
	 * Method that returns shape at specific index
	 * @param index
	 * @return
	 */
	public Shape getShape(int index){
		return shapes.get(index);
	}
	/**
	 * Method that adds shapes to array list
	 * @param newShapes
	 */
	public void addShapes(ArrayList<Shape> newShapes){
		shapes.addAll(newShapes);
	}
	/**
	 * Method that copies shapes to the array list
	 * @param newShapes
	 */
	public void copyShapes (ArrayList<Shape> newShapes)
    {
        shapes = new ArrayList<> (newShapes);
    }
}
