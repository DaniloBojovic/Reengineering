package view;

import java.awt.BorderLayout;
import model.Model;
import controller.Controller;
import view.View;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Point;
import model.Circle;
import model.HexagonAdapter;
import model.Rectangle;
import model.Shape;
import model.Square;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;;

/**
 * Class DrawingFrame 
 * @author Danilo
 *
 */
public class DrawingFrame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup btngrpButtons = new ButtonGroup();
	JPanel pnlNorth = new JPanel();
	
	public JRadioButton rdbtnSelect = new JRadioButton("Select");
	public JRadioButton rdbtnModify = new JRadioButton("Modify");;
	public JRadioButton rdbtnDelete = new JRadioButton("Delete");
	
	public JButton btnUndo = new JButton("UNDO");
	public JButton btnRedo = new JButton("REDO");
	
	JPanel pnlWest = new JPanel();
	public JToggleButton tglbtnPoint = new JToggleButton("");
	public JToggleButton tglbtnLine = new JToggleButton("");
	public JToggleButton tglbtnSquare = new JToggleButton("");
	public JToggleButton tglbtnRectangle = new JToggleButton("");
	public JToggleButton tglbtnCircle = new JToggleButton("");
	public JToggleButton tglbtnHexagon = new JToggleButton("");
	
	JPanel pnlLog = new JPanel();
	public final JTextArea textArea = new JTextArea();

	public View pnlCenter = new View();
	private Model model = new Model();

	public void setModel(Model model) {
		this.model = model;
	}

	private Controller controller = new Controller(this, pnlCenter.getModel());
	private final JPanel panel = new JPanel();
	private final JButton btnOpen_1 = new JButton("Open");
	private final JButton btnSave_1 = new JButton("Save");
	private final JButton btnOpenModel = new JButton("Open Model");
	private final JButton btnSaveModel = new JButton("Save Model");
	private final JButton btnBack = new JButton("Step back");
	private final JButton btnFront = new JButton("Step front");
	private final JButton btnSendToBack = new JButton("Send to back");
	private final JButton btnBringToFront = new JButton("Bring to front");

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame frame = new DrawingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawingFrame() {

		setVisible(true); 
		setLocationRelativeTo(null);
		setTitle("Draw geometric shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 600);
		pnlCenter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		this.getContentPane().add(pnlCenter, BorderLayout.CENTER);


		this.getContentPane().add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[] {74, 61, 65, 63, 77, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_pnlNorth.rowHeights = new int[]{23, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_pnlNorth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		btngrpButtons.add(rdbtnDelete);
		GridBagConstraints gbc_rdbtnDelete = new GridBagConstraints();
		gbc_rdbtnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnDelete.gridx = 4;
		gbc_rdbtnDelete.gridy = 0;
		rdbtnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rdbtnDelete_actionPerformed(e);
			}
		});
		
		btngrpButtons.add(rdbtnModify);
		GridBagConstraints gbc_rdbtnModify = new GridBagConstraints();
		gbc_rdbtnModify.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnModify.gridx = 3;
		gbc_rdbtnModify.gridy = 0;
		rdbtnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rdbbtnModify_actionPerformed(e);
			}
		});
		

		btngrpButtons.add(rdbtnSelect);
		GridBagConstraints gbc_rdbtnSelect = new GridBagConstraints();
		gbc_rdbtnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnSelect.gridx = 2;
		gbc_rdbtnSelect.gridy = 0;
		rdbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlNorth.add(rdbtnSelect, gbc_rdbtnSelect);
		pnlNorth.add(rdbtnModify, gbc_rdbtnModify);
		pnlNorth.add(rdbtnDelete, gbc_rdbtnDelete);
		
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.insets = new Insets(0, 0, 0, 5);
		gbc_btnRedo.gridx = 7;
		gbc_btnRedo.gridy = 0;
		btnRedo.setBackground(Color.LIGHT_GRAY);
		btnRedo.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Redo.png"));
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnRedo_actionPerfrormed(e);
			}
		});
		
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 0, 5);
		gbc_btnUndo.gridx = 6;
		gbc_btnUndo.gridy = 0;
		btnUndo.setBackground(Color.LIGHT_GRAY);
		btnUndo.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Undo.png"));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnUndo_actionPerformed(e);
			}
		});
		pnlNorth.add(btnUndo, gbc_btnUndo);
		pnlNorth.add(btnRedo, gbc_btnRedo);
		
		GridBagConstraints gbc_btnOpenModel = new GridBagConstraints();
		gbc_btnOpenModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenModel.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenModel.gridx = 8;
		gbc_btnOpenModel.gridy = 0;
		btnOpenModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnOpenModel_actionPerformed();
			}
		});
		pnlNorth.add(btnOpenModel, gbc_btnOpenModel);
		
		GridBagConstraints gbc_btnSaveModel = new GridBagConstraints();
		gbc_btnSaveModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveModel.insets = new Insets(0, 0, 0, 5);
		gbc_btnSaveModel.gridx = 9;
		gbc_btnSaveModel.gridy = 0;
		btnSaveModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnSaveModel_actionPerformed();
			}
		});
		pnlNorth.add(btnSaveModel, gbc_btnSaveModel);


		this.getContentPane().add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[]{0, 0};
		gbl_pnlWest.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlWest.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);

		
		btngrpButtons.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.anchor = GridBagConstraints.SOUTH;
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 1;
		tglbtnPoint.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Point.png"));
		tglbtnPoint.setBackground(Color.WHITE);
		
		pnlWest.add(tglbtnPoint, gbc_tglbtnPoint);


		btngrpButtons.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 2;
		tglbtnLine.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Line.png"));
		tglbtnLine.setBackground(Color.WHITE);
		pnlWest.add(tglbtnLine, gbc_tglbtnLine);


		btngrpButtons.add(tglbtnSquare);
		GridBagConstraints gbc_tglbtnSquare = new GridBagConstraints();
		gbc_tglbtnSquare.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSquare.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSquare.gridx = 0;
		gbc_tglbtnSquare.gridy = 3;
		tglbtnSquare.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Square.png"));
		tglbtnSquare.setBackground(Color.WHITE);
		pnlWest.add(tglbtnSquare, gbc_tglbtnSquare);


		btngrpButtons.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 4;
		tglbtnRectangle.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Rect.png"));
		tglbtnRectangle.setBackground(Color.WHITE);
		pnlWest.add(tglbtnRectangle, gbc_tglbtnRectangle);


		btngrpButtons.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 5;
		tglbtnCircle.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Circle.png"));
		tglbtnCircle.setBackground(Color.WHITE);
		pnlWest.add(tglbtnCircle, gbc_tglbtnCircle);


		btngrpButtons.add(tglbtnHexagon);
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 6;
		tglbtnHexagon.setIcon(new ImageIcon("C:\\Users\\igor\\Downloads\\go4code-master-6818eda9944da56fc0fc5744f5e409be6bf87ac4\\01\\start\\DaniloRis\\bin\\shapeImages\\Hex.png"));
		tglbtnHexagon.setBackground(Color.WHITE);
		pnlWest.add(tglbtnHexagon, gbc_tglbtnHexagon);


		pnlCenter.setBackground(Color.WHITE);
		this.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setDrawingFrame(this);				
		pnlCenter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.pnlCenter_mouseClicked(e);
			}
		});


		pnlLog.setBorder(new TitledBorder(null, "Log", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		this.getContentPane().add(pnlLog, BorderLayout.SOUTH);
		textArea.setRows(6);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setSize(760, 20000);

		JScrollPane scrollPane = new JScrollPane(textArea);
		pnlLog.add(scrollPane);
		
		getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_btnOpen_1 = new GridBagConstraints();
		gbc_btnOpen_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpen_1.gridx = 0;
		gbc_btnOpen_1.gridy = 0;
		btnOpen_1.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller open method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnOpen_actionPerformed();
			}
		});
		panel.add(btnOpen_1, gbc_btnOpen_1);
		
		GridBagConstraints gbc_btnSave_1 = new GridBagConstraints();
		gbc_btnSave_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave_1.gridx = 0;
		gbc_btnSave_1.gridy = 1;
		btnSave_1.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller save method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnSave_actionPerformed();
			}
		});
		panel.add(btnSave_1, gbc_btnSave_1);
		
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 2;
		btnBack.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller step back method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnBack_actionPerformed();
			}
		});
		panel.add(btnBack, gbc_btnBack);
		
		GridBagConstraints gbc_btnFront = new GridBagConstraints();
		gbc_btnFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnFront.gridx = 0;
		gbc_btnFront.gridy = 3;
		btnFront.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller step front method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnFront_actionPerformed();
			}
		});
		panel.add(btnFront, gbc_btnFront);
		
		GridBagConstraints gbc_btnSendToBack = new GridBagConstraints();
		gbc_btnSendToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnSendToBack.gridx = 0;
		gbc_btnSendToBack.gridy = 4;
		btnSendToBack.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller send to back method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnSendToBack_actionPerformed();
			}
		});
		panel.add(btnSendToBack, gbc_btnSendToBack);
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 5;
		btnBringToFront.addActionListener(new ActionListener() {
			/**
			 * Listener used to call controller bring to front method on click
			 */
			public void actionPerformed(ActionEvent e) {
				controller.btnBringToFront_actionPerformed();
			}
		});
		panel.add(btnBringToFront, gbc_btnBringToFront);

	}

}
