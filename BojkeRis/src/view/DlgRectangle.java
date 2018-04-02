package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Class DlgSquare
 * @author Danilo
 *
 */
public class DlgRectangle extends JDialog {

	private final JPanel pnlRect = new JPanel();
	public JTextField txtX;
	public JTextField txtY;
	public JTextField txtHeight;
	public JTextField txtWidth;

	public String xTopLeft;
	public String yTopLeft;
	public String height;
	public String width;
	public Color outsideColor = (Color.black);
	public Color insideColor = (Color.white);
	public JButton btnInsideColor = new JButton("");
	public JButton btnOutsideColor = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle(Frame f) {
		super(f, true);
		setTitle("Rectangle");
		setResizable(false);
		setBounds(100, 100, 285, 266);
		getContentPane().setLayout(new BorderLayout());
		pnlRect.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlRect, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		pnlRect.setLayout(null);
		{
			JLabel label = new JLabel("Top left coordinates");
			label.setBounds(48, 11, 114, 14);
			pnlRect.add(label);
		}
		{
			JLabel lblX = new JLabel("X:");
			lblX.setBounds(70, 36, 16, 14);
			pnlRect.add(lblX);
		}
		{
			txtX = new JTextField();
			txtX.setColumns(10);
			txtX.setBounds(140, 30, 86, 20);
			pnlRect.add(txtX);
		}
		{
			JLabel lblY = new JLabel("Y:");
			lblY.setBounds(70, 64, 16, 14);
			pnlRect.add(lblY);
		}
		{
			txtY = new JTextField();
			txtY.setColumns(10);
			txtY.setBounds(140, 58, 86, 20);
			pnlRect.add(txtY);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setBounds(48, 89, 54, 14);
			pnlRect.add(lblHeight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.setColumns(10);
			txtHeight.setBounds(140, 86, 86, 20);
			pnlRect.add(txtHeight);
		}
		{
			JLabel lblInsCol = new JLabel("Inside Color:");
			lblInsCol.setBounds(150, 153, 78, 14);
			pnlRect.add(lblInsCol);
		}
		{

			btnInsideColor.addActionListener(new ActionListener() {
				/**
				 * Method used for choosing an inside color by JColorChooser
				 */
				public void actionPerformed(ActionEvent e) {
					insideColor = JColorChooser.showDialog(null, "Choose color", insideColor);
					btnInsideColor.setBackground(insideColor);
				}
			});
			btnInsideColor.setBounds(226, 153, 28, 28);
			pnlRect.add(btnInsideColor);
		}
		{
			JLabel lblOutColor = new JLabel("Outside Color:");
			lblOutColor.setBounds(10, 153, 86, 14);
			pnlRect.add(lblOutColor);
		}
		{

			btnOutsideColor.addActionListener(new ActionListener() {
				/**
				 * Method used for choosing an outside color by JColorChooser
				 */
				public void actionPerformed(ActionEvent arg0) {
					outsideColor = JColorChooser.showDialog(null, "Choose color", outsideColor);
					btnOutsideColor.setBackground(outsideColor);
				}
			});
			btnOutsideColor.setBounds(98, 153, 28, 28);
			pnlRect.add(btnOutsideColor);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setBounds(48, 117, 64, 14);
			pnlRect.add(lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setBounds(140, 114, 86, 20);
			pnlRect.add(txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					/**
					 * Method that confirms user choice
					 */
					public void actionPerformed(ActionEvent e) {
						xTopLeft = txtX.getText();
						yTopLeft = txtY.getText();
						height = txtHeight.getText();
						width = txtWidth.getText();

						if(xTopLeft.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Integer.parseInt(xTopLeft);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(yTopLeft.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert Y coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Integer.parseInt(yTopLeft);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(height.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert the value for height.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Integer.parseInt(height);

							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Height must be number!", "Error", JOptionPane.ERROR_MESSAGE);

						}
						if(width.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert the value for width.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Integer.parseInt(width);

							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Width must be number!", "Error", JOptionPane.ERROR_MESSAGE);

						}
						try {
							Integer.parseInt(xTopLeft);
							Integer.parseInt(yTopLeft);
							Integer.parseInt(height);
							Integer.parseInt(width);
							repaint();
							setVisible(false);
						}

						catch (Exception e2) {
							// TODO: handle exception
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					/**
					 * Method that cancels user choice
					 */
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
