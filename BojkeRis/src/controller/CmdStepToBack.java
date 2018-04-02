package controller;

import java.util.Collections;

import model.Model;
/**
 * Class which functionality is to move object one step back using command pattern
 * @author Danilo
 *
 */
public class CmdStepToBack implements Command {

	private Model model;
	private int index;
	/**
	 * Conctructor that takes model and index of the shape selected for moving one step to the back
	 * @param model
	 * @param index
	 */	
	public CmdStepToBack(Model model, int index) {
		this.model = model;
		this.index = index;
	}
	/**
	 * Method that moves shape one step back
	 */
	@Override
	public void execute() {
		
		int index2 = index - 1;
		if(index2 >= 0)
			Collections.swap(model.getShapes(), index, index2);		
	}
	/**
	 * Method that moves shape one step to the front (undo method)
	 */
	@Override
	public void unexecute() {
		execute();
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return "Step to back Z-order ";
	}
}
