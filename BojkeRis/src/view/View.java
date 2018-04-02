package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import model.Model;
import model.Shape;
import model.SurfaceShape;
/**
 * Class View used for MVC pattern
 * @author Danilo
 *
 */
public class View extends JPanel {

	private Model model = new Model();
	private DrawingFrame drawingFrame;
	private ArrayList<Shape> shapes;

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Model getModel() {
		return model;
	}

	public DrawingFrame getDrawingFrame() {
		return drawingFrame;
	}

	public void setDrawingFrame(DrawingFrame drawingFrame) {
		this.drawingFrame = drawingFrame;
	}
	/**
	 * Method that overrides paint method 
	 */
	public void paint(Graphics g){
		super.paint(g);

		if (model.getShapes().size() > 0) {
			Iterator it = model.getShapes().iterator();

			while (it.hasNext()) {
				Shape shape = (Shape)it.next();
				shape.drawShape(g);
				if (shape instanceof SurfaceShape) {
					shape.fillMethod(g);
				}
			}
		}
	}

}
