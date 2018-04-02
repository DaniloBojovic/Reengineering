package controller;

import model.Model;
import model.Shape;
/**
 * Class which functionality is to delete shapes from panel using command pattern
 * @author Danilo
 *
 */
public class CmdDeleteShape implements Command {
	
	private Model model;
	private Shape shape;
	/**
	 * Contructor that set model and shape for deletion
	 * @param model
	 * @param shape
	 */
	public CmdDeleteShape(Model model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	/**
	 * Method used for deleting methods
	 */
	@Override
	public void execute() {
		model.remove(shape);
	}
	/**
	 * Method used for adding deleted shapes on panel (undo method)
	 */
	@Override
	public void unexecute() {
		model.add(shape);
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return "Deleted: " + shape.toString();
	}
}
