package controller;

import model.Model;
import model.Shape;
/**
 * Class that sends shape to the bottom using command pattern
 * @author Danilo
 *
 */
public class CmdSendToBack implements Command {

	private Model model;
	private int index;
	private Shape shape;
	/**
	 * Conctructor that takes model and index of the selected shape 
	 * @param model
	 * @param index
	 */
	public CmdSendToBack(Model model, int index) {
		this.model = model;
		this.index = index;
		this.shape = model.getSelectedShape(0); //first selected shape
	}
	/**
	 * Method that sends shape to the bottom
	 */
	@Override
	public void execute() {
		model.addForFrontBack(shape, 0); //adding shape to zero element
		model.remove(index + 1);
	}
	/**
	 * Method that sends shape to the top (undo method)
	 */
	@Override
	public void unexecute() {
		model.remove(0);
		model.addForFrontBack(shape, index);
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return "Send to back Z-order";
	}
}
