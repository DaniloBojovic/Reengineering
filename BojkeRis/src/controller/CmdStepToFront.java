package controller;

import java.util.Collections;

import model.Model;
/**
 * Class which functionality is to move object one step to the front using command pattern
 * @author Danilo
 *
 */
public class CmdStepToFront implements Command {

	private Model model;
	private int index;
	/**
	 * Conctructor that takes model and index of the shape selected for moving one step to the front
	 * @param model
	 * @param index
	 */
	public CmdStepToFront(Model model, int index) {
		this.model = model;
		this.index = index;
	}
	/**
	 * Method for moving shape one step to the front
	 */
	@Override
	public void execute() {
		
		int index2 = index + 1;
		
		if(index2 < model.getShapes().size())
			Collections.swap(model.getShapes(), index, index2);		
	}
	/**
	 * Method that moves shape one step back (undo method)
	 */
	@Override
	public void unexecute() {
		execute();
	}
	/**
	 * Method that writes log informations
	 */
	public String toString(){
		return "Step to front Z-order ";
	}
}
