package controller;

import model.Shape;
/**
 * Class which functionality is to modify shapes on panel using command pattern
 * @author Danilo
 *
 */
public class CmdUpdateShape implements Command {

	private Shape currentShape;
    private Shape newShape;
    private Shape oldShape;
    /**
     * Contructor that takes current shape and the new shape
     * @param currentShape
     * @param newShape
     */
	public CmdUpdateShape(Shape currentShape, Shape newShape) {
		this.currentShape = currentShape;
		this.newShape = newShape;
	}
	/**
	 * Method used to change shape to a new shape
	 */
	@Override
	public void execute() {
		oldShape = currentShape.copyShape();
		currentShape.changeShape(newShape);
	}
	/**
	 * Method used to change new shape to old shape (undo method)
	 */
	@Override
	public void unexecute() {
		currentShape.changeShape(oldShape);
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return currentShape.toString();
	}
}
