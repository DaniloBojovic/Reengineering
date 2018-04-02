package controller;

import model.Model;
import model.Shape;
/**
 * Class which functionality is to add  shapes on panel using command pattern
 * @author Danilo
 *
 */
public class CmdAddShape implements Command {

	private Model model;
	private Shape shape;
	/**
	* Contructor that sets model and shape
	* @param model
	* @param shape
	*/
	public CmdAddShape(Model model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	/**
	* Method used for adding shapes
	*/
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(shape);
	}
	/**
	* Method used for removing added shapes (undo method)
	*/
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
	}
	/**
	* Method that writes log informations
	*/
	public String toString(){
		return shape.toString();
	}
}
