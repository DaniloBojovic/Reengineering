package controller;
/**
 * Command interface used for implementing Command pattern
 * @author Danilo
 *
 */
public interface Command {
	/**
	 * Method used for executing commands
	 */
	void execute();
	/**
	 * Method used for unexecuting commands
	 */
	void unexecute();
}
