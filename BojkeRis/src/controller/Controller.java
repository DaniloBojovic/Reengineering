package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Circle;
import model.HexagonAdapter;
import model.Line;
import model.Model;
import model.Point;
import model.Rectangle;
import model.Shape;
import model.Square;
import model.SurfaceShape;
import view.DlgCircle;
import view.DlgHexagonAdapter;
import view.DlgLine;
import view.DlgPoint;
import view.DlgRectangle;
import view.DlgSquare;
import view.DrawingFrame;
import view.View;
/**
 * Controller class 
 * @author Danilo
 *
 */
public class Controller {
	private DrawingFrame drawingFrame;
	private Model model = new Model();
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	public Color outsideColor = (Color.black);
	public Color insideColor = (Color.white);
	public View pnlCenter;
	public static int counter;
	boolean b;
	Point point;
	Line line;
	Circle circle;
	Square square;
	Rectangle rect;
	HexagonAdapter hex;
	int mouseClicked;
	ArrayList<Shape> shapes = model.getShapes();
	private static final Logger logger = Logger.getLogger("My logger");
	public static String[] val = new String[100];
	public static String[] del = new String[100];
	public static int v, d;
	ArrayList<Shape> newShapes;
	public boolean deleted;
	/**
	 * Contructor that takes two parameters - DrawingFrame and Model objects
	 * @param drawingFrame
	 * @param model
	 */
	public Controller(DrawingFrame drawingFrame, Model model) {
		this.drawingFrame = drawingFrame;
		this.model = model;
	}

	public DrawingFrame getDrawingFrame() {
		return drawingFrame;
	}

	public void setDrawingFrame(DrawingFrame drawingFrame) {
		this.drawingFrame = drawingFrame;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	/**
	 * Method used for drawing chosen shapes on mouse click
	 * @param e MouseEvent
	 */
	public void pnlCenter_mouseClicked(MouseEvent e) {

		drawingFrame.repaint();
		int x = e.getX();
		int y = e.getY();

		// SELECT
		if (drawingFrame.rdbtnSelect.isSelected()) {

			ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
			if (model.getShapes().size() > 0) {
				while (it.hasPrevious()) {
					Shape shape = (Shape) it.previous();
					if (shape.contains(x, y)) {
						if (shape.isSelected() == true) {
							shape.setSelected(false);
							counter--;
							break;
						} else {
							shape.setSelected(true);
							model.addToSelection(shape);
							counter++;
							b = true;
						}

						break;
					}
				}
				drawingFrame.repaint();
			}
		}

		// Draw point
		else if (drawingFrame.tglbtnPoint.isSelected()) {
			point = new Point(x, y);
			Color color = JColorChooser.showDialog(null, "Choose color", outsideColor);
			if (color != null) {
				point.setColor(color);
				CmdAddShape cmd = new CmdAddShape(model, point);
				doCommand(cmd);
				drawingFrame.textArea.append(cmd.toString() + '\n');
				drawingFrame.repaint();
			}
		}
		// Draw line
		else if (drawingFrame.tglbtnLine.isSelected()) {
			if (mouseClicked % 2 == 0) {
				point = new Point(e.getX(), e.getY());
				mouseClicked++;
			}

			else {
				Color color = JColorChooser.showDialog(null, "Choose color", outsideColor);
				if (color != null) {
					outsideColor = color;
					line = new Line(point, new Point(e.getX(), e.getY()), outsideColor);
					mouseClicked = 0;

					CmdAddShape cmd = new CmdAddShape(model, line);
					doCommand(cmd);
					shapes.add(line);
					drawingFrame.textArea.append(cmd.toString() + '\n');
					drawingFrame.repaint();
				} else {
					mouseClicked--;
				}
			}
		}
		// Draw square
		else if (drawingFrame.tglbtnSquare.isSelected()) {
			DlgSquare dlg = new DlgSquare(drawingFrame);
			dlg.setLocationRelativeTo(null);
			dlg.txtXTopLeft.setText(String.valueOf(e.getX()));
			dlg.txtYTopLeft.setText(String.valueOf(e.getY()));
			dlg.txtXTopLeft.setEditable(false);
			dlg.txtYTopLeft.setEditable(false);
			dlg.setVisible(true);

			try {
				point = new Point(x, y);
				square = new Square(point, Integer.parseInt(dlg.side), dlg.insideColor, dlg.outsideColor);
				CmdAddShape cmd = new CmdAddShape(model, square);
				doCommand(cmd);
				shapes.add(square);
				drawingFrame.textArea.append(cmd.toString() + '\n');
				drawingFrame.repaint();

			} catch (NumberFormatException e2) {
				System.out.println("Cancellation");
			}
		}
		// Draw rectangle
		else if (drawingFrame.tglbtnRectangle.isSelected()) {
			DlgRectangle dlg = new DlgRectangle(drawingFrame);
			dlg.setLocationRelativeTo(null);
			dlg.txtX.setText(String.valueOf(e.getX()));
			dlg.txtY.setText(String.valueOf(e.getY()));
			dlg.txtX.setEditable(false);
			dlg.txtY.setEditable(false);
			dlg.setVisible(true);

			try {
				point = new Point(x, y);
				rect = new Rectangle(point, Integer.parseInt(dlg.width), Integer.parseInt(dlg.height), dlg.insideColor,
						dlg.outsideColor);
				CmdAddShape cmd = new CmdAddShape(model, rect);
				doCommand(cmd);
				shapes.add(rect);
				drawingFrame.textArea.append(cmd.toString() + '\n');
				drawingFrame.repaint();

			} catch (NumberFormatException e2) {
				System.out.println("Cancellation");
			}
		}
		// Draw Circle
		else if (drawingFrame.tglbtnCircle.isSelected()) {

			DlgCircle dlg = new DlgCircle(drawingFrame);
			dlg.setLocationRelativeTo(null);
			dlg.txtXCenter.setText(String.valueOf(e.getX()));
			dlg.txtYCenter.setText(String.valueOf(e.getY()));
			dlg.txtXCenter.setEditable(false);
			dlg.txtYCenter.setEditable(false);
			dlg.getRootPane().setDefaultButton(dlg.okButton);
			dlg.setVisible(true);
			try {
				point = new Point(x, y);
				circle = new Circle(point, Integer.parseInt(dlg.radius), dlg.insideColor, dlg.outsideColor);
				CmdAddShape cmd = new CmdAddShape(model, circle);
				doCommand(cmd);
				shapes.add(circle);
				drawingFrame.textArea.append(cmd.toString() + '\n');
				drawingFrame.repaint();

			} catch (NumberFormatException e2) {
				System.out.println("Cancellation");
			}
		}
		// Draw Hex
		else if (drawingFrame.tglbtnHexagon.isSelected()) {

			DlgHexagonAdapter dlg = new DlgHexagonAdapter(drawingFrame);
			dlg.setLocationRelativeTo(null);
			dlg.txtX.setText(String.valueOf(e.getX()));
			dlg.txtY.setText(String.valueOf(e.getY()));
			dlg.txtX.setEditable(false);
			dlg.txtY.setEditable(false);
			dlg.setVisible(true);

			try {
				point = new Point(x, y);
				hex = new HexagonAdapter(x, y, Integer.parseInt(dlg.radius));
				hex.setColor(dlg.outsideColor);
				hex.setFillColor(dlg.insideColor);
				CmdAddShape cmd = new CmdAddShape(model, hex);
				doCommand(cmd);
				shapes.add(hex);
				drawingFrame.textArea.append(cmd.toString() + '\n');
				drawingFrame.repaint();
			} catch (NumberFormatException e1) {
				System.out.println("Cancellation");
			}
		}
	}
	/**
	 * Method used for modification of selected shape
	 * @param e 
	 */
	// Modify
	public void rdbbtnModify_actionPerformed(ActionEvent e) {

		Iterator<Shape> it = model.getShapes().iterator();
		if (model.getShapes().size() > 0 && counter < 2) {
			while (it.hasNext()) {
				Shape o = (Shape) it.next();
				if (o.isSelected()) {
					if (o instanceof Point) {
						DlgPoint dlg = new DlgPoint(drawingFrame);
						dlg.txtX.setText(String.valueOf(((Point) o).getX()));
						dlg.txtY.setText(String.valueOf(((Point) o).getY()));
						dlg.btnColor.setBackground(((Point) o).getColor());
						dlg.color = dlg.btnColor.getBackground();
						dlg.setVisible(true);
						try {
							String str = o.toString();
							CmdUpdateShape cmd = new CmdUpdateShape((Point) o,
									new Point(Integer.parseInt(dlg.x), Integer.parseInt(dlg.y), dlg.color));
							doCommand(cmd);
							// drawingFrame.textArea.append(cmd.toString() +
							// '\n');
							drawingFrame.textArea.setText(drawingFrame.textArea.getText().replace(str, cmd.toString()));
							counter--;
							// counter--;
						} catch (NumberFormatException ex) {
							System.out.println("Cancellation");
						}
					} else if (o instanceof Line) {
						DlgLine dlg = new DlgLine(drawingFrame);
						dlg.txtXStart.setText(String.valueOf(((Line) o).getStartPoint().getX()));
						dlg.txtYStart.setText(String.valueOf(((Line) o).getStartPoint().getY()));
						dlg.txtXEnd.setText(String.valueOf(((Line) o).getEndPoint().getX()));
						dlg.txtYEnd.setText(String.valueOf(((Line) o).getEndPoint().getY()));
						dlg.btnColor.setBackground(((Line) o).getColor());
						dlg.setVisible(true);
						try {
							String str = o.toString();
							CmdUpdateShape cmd = new CmdUpdateShape((Line) o,
									new Line(new Point(Integer.parseInt(dlg.xStart), Integer.parseInt(dlg.yStart)),
											new Point(Integer.parseInt(dlg.xEnd), Integer.parseInt(dlg.yEnd)),
											dlg.btnColor.getBackground()));
							doCommand(cmd);
							//drawingFrame.textArea.append(cmd.toString() + '\n');
							drawingFrame.textArea.setText
							(drawingFrame.textArea.getText()
									.replace(str, cmd.toString()));
							//counter--;
						} catch (NumberFormatException ex) {
							System.out.println("Cancellation");
						}
					} else if (o instanceof SurfaceShape) {
						if (o instanceof Circle) {
							DlgCircle dlg = new DlgCircle(drawingFrame);
							dlg.txtXCenter.setText(String.valueOf(((Circle) o).getCenter().getX()));
							dlg.txtYCenter.setText(String.valueOf(((Circle) o).getCenter().getY()));
							dlg.txtRadius.setText(String.valueOf(((Circle) o).getRadius()));
							dlg.btnOutsideColor.setBackground(((Circle) o).getColor());
							dlg.btnInsideColor.setBackground(((Circle) o).getFillColor());
							dlg.insideColor = dlg.btnInsideColor.getBackground();
							dlg.outsideColor = dlg.btnOutsideColor.getBackground();
							dlg.setVisible(true);
							try {
								String str = o.toString();
								CmdUpdateShape cmd = new CmdUpdateShape((Circle) o,
										new Circle(
												new Point(Integer.parseInt(dlg.xCenter), Integer.parseInt(dlg.yCenter)),
												Integer.parseInt(dlg.radius), dlg.insideColor, dlg.outsideColor));
								doCommand(cmd);
								//drawingFrame.textArea.append(cmd.toString() + '\n');
								drawingFrame.textArea.setText
								(drawingFrame.textArea.getText()
										.replace(str, cmd.toString()));
								//counter--;
							} catch (NumberFormatException ex) {
								System.out.println("Cancellation");
							}
						} else if (o instanceof Rectangle) {
							DlgRectangle dlg = new DlgRectangle(drawingFrame);
							dlg.txtX.setText(String.valueOf(((Rectangle) o).getTopLeft().getX()));
							dlg.txtY.setText(String.valueOf(((Rectangle) o).getTopLeft().getY()));
							dlg.txtWidth.setText(String.valueOf(((Rectangle) o).getSide()));
							dlg.txtHeight.setText(String.valueOf(((Rectangle) o).getRectHeight()));
							dlg.btnOutsideColor.setBackground(((Rectangle) o).getColor());
							dlg.btnInsideColor.setBackground(((Rectangle) o).getFillColor());
							dlg.insideColor = dlg.btnInsideColor.getBackground();
							dlg.outsideColor = dlg.btnOutsideColor.getBackground();
							dlg.setVisible(true);
							try {
								String str = o.toString();
								CmdUpdateShape cmd = new CmdUpdateShape((Rectangle) o,
										new Rectangle(
												new Point(Integer.parseInt(dlg.xTopLeft),
														Integer.parseInt(dlg.yTopLeft)),
												Integer.parseInt(dlg.width), Integer.parseInt(dlg.height),
												dlg.insideColor, dlg.outsideColor));
								doCommand(cmd);
								//drawingFrame.textArea.append(cmd.toString() + '\n');
								drawingFrame.textArea.setText
								(drawingFrame.textArea.getText()
										.replace(str, cmd.toString()));
								//counter--;
							} catch (NumberFormatException ex) {
								System.out.println("Cancellation");
							}
						} else if (o instanceof Square) {
							DlgSquare dlg = new DlgSquare(drawingFrame);
							dlg.txtXTopLeft.setText(String.valueOf(((Square) o).getTopLeft().getX()));
							dlg.txtYTopLeft.setText(String.valueOf(((Square) o).getTopLeft().getY()));
							dlg.txtSide.setText(String.valueOf(((Square) o).getSide()));
							dlg.btnOutsideColor.setBackground(((Square) o).getColor());
							dlg.btnInsideColor.setBackground(((Square) o).getFillColor());
							dlg.insideColor = dlg.btnInsideColor.getBackground();
							dlg.outsideColor = dlg.btnOutsideColor.getBackground();
							dlg.setVisible(true);
							try {
								String str = o.toString();
								CmdUpdateShape cmd = new CmdUpdateShape((Square) o,
										new Square(
												new Point(Integer.parseInt(dlg.xTopLeft),
														Integer.parseInt(dlg.yTopLeft)),
												Integer.parseInt(dlg.side), dlg.insideColor, dlg.outsideColor));
								doCommand(cmd);
								//drawingFrame.textArea.append(cmd.toString() + '\n');
								drawingFrame.textArea.setText
								(drawingFrame.textArea.getText()
										.replace(str, cmd.toString()));
								//counter--;
							} catch (NumberFormatException ex) {
								System.out.println("Cancellation");
							}
						} else if (o instanceof HexagonAdapter) {
							DlgHexagonAdapter dlg = new DlgHexagonAdapter(drawingFrame);
							dlg.txtX.setText(String.valueOf(((HexagonAdapter) o).getX()));
							dlg.txtY.setText(String.valueOf(((HexagonAdapter) o).getY()));
							dlg.txtRadius.setText(String.valueOf(((HexagonAdapter) o).getR()));
							dlg.btnOutsideColor.setBackground(((HexagonAdapter) o).getColor());
							dlg.btnInsideColor.setBackground(((HexagonAdapter) o).getFillColor());
							dlg.insideColor = dlg.btnInsideColor.getBackground();
							dlg.outsideColor = dlg.btnOutsideColor.getBackground();
							dlg.setVisible(true);
							try {
								String str = o.toString();
								CmdUpdateShape cmd = new CmdUpdateShape((HexagonAdapter) o,
										new HexagonAdapter(Integer.parseInt(dlg.xCenter), Integer.parseInt(dlg.yCenter),
												Integer.parseInt(dlg.radius), dlg.insideColor, dlg.outsideColor));
								doCommand(cmd);
								//drawingFrame.textArea.append(cmd.toString() + '\n');
								drawingFrame.textArea.setText
								(drawingFrame.textArea.getText()
										.replace(str, cmd.toString()));
								//counter--;
							} catch (NumberFormatException ex) {
								System.out.println("Cancellation");
							}
						}
					}
				} 
				else if (!it.hasNext() && counter < 1) {
					JOptionPane.showMessageDialog(null, "Select a shape for modification", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} 
				else if (!it.hasNext() && b) {
					b = false;
					/*JOptionPane.showMessageDialog(null, "Select a shape for modification",
							"Info", JOptionPane.INFORMATION_MESSAGE);*/
					break;
				} 

			}
			drawingFrame.repaint();
		} else {
			if (model.getShapes().size() > 0) {
				JOptionPane.showMessageDialog(null, "Multiple modification is not allowed", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * Method used for deleting selected shapes
	 * @param e  
	 */
	// Delete
	public void rdbtnDelete_actionPerformed(ActionEvent e) {
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			Shape shape = it.next();
			if (shape.isSelected()) {
				if (shape instanceof Point) {
					int p = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == p) {
						it.remove();
						deleted = true;
						CmdDeleteShape cmd = new CmdDeleteShape(model, (Point) shape);
						doCommand(cmd);
						shapes.remove(shape);
						del[d++] = shape.toString();
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						//drawingFrame.textArea.replaceSelection(cmd.toString() + '\n');
						drawingFrame.textArea.setText
							(drawingFrame.textArea.getText()
									.replace(shape.toString(), "Deleted"));
						counter--;
					}
				} else if (shape instanceof Line) {
					int p = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == p) {
						it.remove();
						CmdDeleteShape cmd = new CmdDeleteShape(model, (Line) shape);
						doCommand(cmd);
						shapes.remove(shape);
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						drawingFrame.textArea.setText
						(drawingFrame.textArea.getText()
								.replace(shape.toString(), "Deleted"));
						counter--;
					}
				} else if (shape instanceof Circle) {
					int p = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == p) {
						it.remove();
						deleted = true;
						CmdDeleteShape cmd = new CmdDeleteShape(model, (Circle) shape);
						doCommand(cmd);
						shapes.remove(shape);
						shape.setSelected(false);
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						drawingFrame.textArea.setText
						(drawingFrame.textArea.getText()
								.replace(shape.toString(), "Deleted"));
						counter--;
					}
				} else if (shape instanceof Rectangle) {
					int p = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == p) {
						it.remove();
						CmdDeleteShape cmd = new CmdDeleteShape(model, (Rectangle) shape);
						doCommand(cmd);
						shapes.remove(shape);
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						drawingFrame.textArea.setText
						(drawingFrame.textArea.getText()
								.replace(shape.toString(), "Deleted"));
						counter--;
					}
				} else if (shape instanceof Square) {
					int p = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == p) {
						it.remove();
						CmdDeleteShape cmd = new CmdDeleteShape(model, (Square) shape);
						doCommand(cmd);
						shapes.remove(shape);
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						drawingFrame.textArea.setText
						(drawingFrame.textArea.getText()
								.replace(shape.toString(), "Deleted"));
						counter--;
					}
				} else if (shape instanceof HexagonAdapter) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					if (JOptionPane.OK_OPTION == result) {
						it.remove();
						CmdDeleteShape cmd = new CmdDeleteShape(model, (HexagonAdapter) shape);
						doCommand(cmd);
						shapes.remove(shape);
						//drawingFrame.textArea.append(cmd.toString() + '\n');
						drawingFrame.textArea.setText
						(drawingFrame.textArea.getText()
								.replace(shape.toString(), "Deleted"));
						counter--;
					}
				}
			} else if (!it.hasNext() && b) {
				b = false;
				break;
			} else if (!it.hasNext() && counter < 1 && !deleted) {
				JOptionPane.showMessageDialog(null, "Select a shape for deletion", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
		drawingFrame.repaint();
	}

	/**
	 * Method used for Undo option
	 * @param e
	 */
	// Undo
	public void btnUndo_actionPerformed(ActionEvent e) {
		if (!undoStack.isEmpty()) {
			Command cmd = undoStack.pop();
			cmd.unexecute();
			redoStack.push(cmd);			
			logger.log(Level.INFO, "UNDO: " + cmd.toString());
			val[v] = cmd.toString() + " *";
			drawingFrame.textArea.setText
			(drawingFrame.textArea.getText()
					.replace(cmd.toString(), val[v]));
			
			drawingFrame.textArea.append("UNDO" + " " + val[v++] + '\n');
			drawingFrame.repaint();
		}
	}
	/**
	 * Method used for Redo option
	 * @param e
	 */
	// Redo
	public void btnRedo_actionPerfrormed(ActionEvent e) {
		if (!redoStack.isEmpty()) {
			Command cmd = redoStack.pop();
			cmd.execute();
			undoStack.push(cmd);			
			logger.log(Level.INFO, "REDO: " + cmd.toString());
			drawingFrame.textArea.setText
			(drawingFrame.textArea.getText()
					.replace(val[--v], cmd.toString()));
			drawingFrame.textArea.append("REDO" + val[v] + '\n');
			drawingFrame.repaint();
		}
	}
	/**
	 * Method that executes Command patern
	 * @param cmd
	 */
	// Do command
	public void doCommand(Command cmd) {
		
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();		
		logger.log(Level.INFO, cmd.toString());
	}
	/**
	 * Method used for saving log files
	 */
	public void btnSave_actionPerformed() {

		BufferedWriter bw = null;
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(file);
		fileChooser.setFileFilter(new FileNameExtensionFilter(".log", "log"));

		int returnVal = fileChooser.showSaveDialog(pnlCenter);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			file = new File(fileChooser.getSelectedFile().getAbsolutePath());

			String fname = file.getAbsolutePath();

			if (!fname.endsWith(".log")) {
				file = new File(fname + ".log");
			}
			try {
				bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				drawingFrame.textArea.write(bw);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * Method used for opening log files 
	 */
	public void btnOpen_actionPerformed(){
		
		StringBuffer stringBuffer = new StringBuffer();
		JFileChooser fileChooser = new JFileChooser();
		
		int returnVal = fileChooser.showOpenDialog(pnlCenter);
		if(returnVal == JFileChooser.APPROVE_OPTION){

			try {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				FileReader fileReader = new FileReader(file);		
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				while((line = bufferedReader.readLine()) != null){
					stringBuffer.append(line);
					stringBuffer.append("\n");
				}
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Regex metod that extracting numbers from file
			String strFromFile[] = stringBuffer.toString().split("\n");
			logWriter(strFromFile);
			/*System.out.println(strArray[1]);
			System.out.println(strArray[0]);*/			
		}
		else if(returnVal == JFileChooser.CANCEL_OPTION){
			
		}
	}
	/**
	 * Method used for retrieving strings from file
	 * @param fileString
	 */
	public void logWriter(String[] fileString){
		
		for(String s: fileString){
			if(s.startsWith("P") && !s.endsWith("*")){//
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color color = new Color(Integer.parseInt(xy[2]));
				point = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), color);
				CmdAddShape cmd = new CmdAddShape(model, point);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("L")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color color = new Color(Integer.parseInt(xy[4]));
				line = new Line(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						new Point(Integer.parseInt(xy[2]), Integer.parseInt(xy[3])), color);
				CmdAddShape cmd = new CmdAddShape(model, line);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("C")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				circle = new Circle(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, circle);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("S")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				square = new Square(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, square);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("Rec")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[4]));
				Color color = new Color(Integer.parseInt(xy[5]));
				rect = new Rectangle(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), Integer.parseInt(xy[3]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, rect);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("H")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				hex = new HexagonAdapter(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, hex);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: P")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color color = new Color(Integer.parseInt(xy[2]));
				point = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), color);
				CmdAddShape cmd = new CmdAddShape(model, point);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: L")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color color = new Color(Integer.parseInt(xy[4]));
				line = new Line(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						new Point(Integer.parseInt(xy[2]), Integer.parseInt(xy[3])), color);
				CmdAddShape cmd = new CmdAddShape(model, line);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: C")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				circle = new Circle(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, circle);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: S")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				square = new Square(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, square);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: Rec")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[4]));
				Color color = new Color(Integer.parseInt(xy[5]));
				rect = new Rectangle(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), Integer.parseInt(xy[3]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, rect);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: Rec")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[4]));
				Color color = new Color(Integer.parseInt(xy[5]));
				rect = new Rectangle(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])), 
						Integer.parseInt(xy[2]), Integer.parseInt(xy[3]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, rect);
				doCommand(cmd);
				drawingFrame.repaint();
			}
			else if(s.startsWith("UNDO Deleted: H")){
				s = s.replaceAll("[^-?0-9]+", " ");
				String[] xy = s.trim().split(" ");
				Color colorFill = new Color(Integer.parseInt(xy[3]));
				Color color = new Color(Integer.parseInt(xy[4]));
				hex = new HexagonAdapter(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), 
						Integer.parseInt(xy[2]), colorFill, color);
				CmdAddShape cmd = new CmdAddShape(model, hex);
				doCommand(cmd);
				drawingFrame.repaint();
			}
		}
	}
	/**
	 * Method used for opening serializable files
	 */
	public void btnOpenModel_actionPerformed(){
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(file);
		chooser.setFileFilter(new FileNameExtensionFilter("ser files", "ser"));
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnVal = chooser.showOpenDialog(pnlCenter);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			file = new File(chooser.getSelectedFile().getAbsolutePath());
			
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
	            Model model1 = (Model) ois.readObject();
	            model.copyShapes(model1.getShapes());
	            drawingFrame.pnlCenter.setShapes(model.getShapes());
	            drawingFrame.repaint();
	            //System.out.println(model1.toString());
	            fis.close();
	            ois.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
                       
		}
	}
	/**
	 * Method used for saving serializable files
	 */
	public void btnSaveModel_actionPerformed(){
		
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(file);
		chooser.setFileFilter(new FileNameExtensionFilter(".ser", ".ser"));
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnVal = chooser.showSaveDialog(pnlCenter);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			file = new File(chooser.getSelectedFile().getAbsolutePath());

			String fname = file.getAbsolutePath();

			if (!fname.endsWith(".ser")) {
				file = new File(fname + ".ser");
			}
			
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(model);
				fos.close();
		        oos.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}                       
		 }
	}
	/**
	 * Method used to move object one step to the front
	 */
	public void btnFront_actionPerformed(){
		
		if(model.getSelection().size() == 1){
			
			int index = model.getShapes().indexOf(model.getSelectedShape(0));
			Command cmd = new CmdStepToFront(model, index);
			doCommand(cmd);
			if(index == 0){
			model.getSelectedShape(index).setSelected(false); //remove selection after change
			drawingFrame.pnlCenter.repaint();
			counter--;
			//System.out.println(model.getSelection().size());//Test
			model.getSelection().clear();
			}
			else{
				model.getSelectedShape(0).setSelected(false);
				counter--;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Select one shape for step front", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			for(int i = 0; i < model.getSelection().size(); i++){
				model.getSelectedShape(i).setSelected(false);
				counter--;
			}
		}
		drawingFrame.repaint();
		model.getSelection().clear();
		//System.out.println(model.getSelection().size()); //Test
	}
	/**
	 * Method used to move object one step back 
	 */
	public void btnBack_actionPerformed(){
		
		if (model.getSelection().size() == 1) {
			
			int index = model.getShapes().indexOf(model.getSelectedShape(0));
			//int index2 = model.getShapes().indexOf(model.getSelection());
			Command cmd = new CmdStepToBack(model, index);
			doCommand(cmd);
			if (index > 0) {
				model.getSelectedShape(0).setSelected(false);
				counter--;
				drawingFrame.pnlCenter.repaint();
				//System.out.println(model.getSelection().size()); //Test
			}
			else{
				model.getSelectedShape(index).setSelected(false);
				counter--;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Select one shape for step back", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			for(int i = 0; i < model.getSelection().size(); i++){
				model.getSelectedShape(i).setSelected(false);
				counter--;
			}
		}
		drawingFrame.repaint();
		model.getSelection().clear();
		//System.out.println(model.getSelection().size()); //Test
	}
	/**
	 * Method used to place object on the top
	 */
	public void btnBringToFront_actionPerformed(){
		
		if (model.getSelection().size() == 1) {
			
			if (model.getShapes().size() > 1) {
								
				int index = model.getShapes().indexOf(model.getSelectedShape(0));
				Shape s = model.getShape(index);
				Command cmd = new CmdBringToFront(model, index);
				doCommand(cmd);
				if(index > 0){
					s.setSelected(false);
					counter--;
				}
				else{
					s.setSelected(false);
					counter--;
				}								
			}
		}
		else{
			Shape s = model.getShape(0);
			JOptionPane.showMessageDialog(null, "Select one shape for bring to front", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			for(int i = 0; i < model.getShapes().size(); i++){
			//for(int i = 0; i <= model.getSelection().size(); i++){
				s = model.getShape(i);
				if(s.isSelected()){
					s.setSelected(false);
					counter--;
				}
			}
		}
		drawingFrame.pnlCenter.repaint();
		drawingFrame.repaint();
		model.getSelection().clear();
	}
	/**
	 * Method used to place object on the bottom 
	 */
	public void btnSendToBack_actionPerformed() {

		if (model.getSelection().size() == 1) {

			if (model.getShapes().size() > 1) {

				int index = model.getShapes().indexOf(model.getSelectedShape(0));
				Shape s = model.getShape(index);
				Command cmd = new CmdSendToBack(model, index);
				doCommand(cmd);
				if (index > 0) {
					s.setSelected(false);
					counter--;
				} else {
					s.setSelected(false);
					counter--;
				}				
			}
		} else {
			Shape s = model.getShape(0);
			JOptionPane.showMessageDialog(null, "Select one shape for bring to front", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			for(int i = 0; i < model.getShapes().size(); i++){
				s = model.getShape(i);
				if(s.isSelected()){
					s.setSelected(false);
					counter--;
				}
			}
		}
		drawingFrame.pnlCenter.repaint();
		drawingFrame.repaint();
		model.getSelection().clear();
	}
}
