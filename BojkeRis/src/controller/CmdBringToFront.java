package controller;

import model.Model;
import model.Shape;
/**
 * Class that brings shape to the top using command pattern
 * @author Danilo
 *
 */
public class CmdBringToFront implements Command {

	private Model model;
	private int index;
	private Shape shape;
	/**
	* Conctructor that takes model and index of the selected shape 
	* @param model
	* @param index
	*/
	public CmdBringToFront(Model model, int index) {
		this.model = model;
		this.index = index;
		this.shape = model.getSelectedShape(0); //shape intialized to first selected value
	}
	/**
	 * Method that brings shape to the top 
	 */
	@Override
	public void execute() {
		model.add(shape);
		model.remove(model.getShape(index));
	}
	/**
	 * Method that brings shape back (undo method)
	 */
	@Override
	public void unexecute() {
		model.remove(shape);
		model.addForFrontBack(shape, index);
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return "Bring to front Z-order";
	}
}
