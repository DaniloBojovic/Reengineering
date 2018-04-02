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
 * Class DlgCircle
 * @author Danilo
 *
 */
public class DlgCircle extends JDialog {

	public final JPanel pnlCircle = new JPanel();
	public JTextField txtXCenter;
	public JTextField txtYCenter;
	public JTextField txtRadius;

	public String xCenter;
	public String yCenter;
	public String radius;
	public Color outsideColor = (Color.black);
	public Color insideColor = (Color.white);

	public JButton okButton = new JButton("OK");
	public JButton btnOutsideColor = new JButton("");
	public JButton btnInsideColor = new JButton("");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle(Frame f) {
		super(f, true);
		setTitle("Circle");
		setResizable(false);
		setBounds(100, 100, 270, 226);
		getContentPane().setLayout(null);
		pnlCircle.setBounds(0, 0, 267, 163);
		pnlCircle.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCircle);
		setLocationRelativeTo(null);

		pnlCircle.setLayout(null);
		{
			JLabel lblX = new JLabel("X:");
			lblX.setBounds(101, 30, 26, 14);
			pnlCircle.add(lblX);
		}
		{
			JLabel lblY = new JLabel("Y:");
			lblY.setBounds(101, 55, 26, 14);
			pnlCircle.add(lblY);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setBounds(62, 86, 46, 14);
			pnlCircle.add(lblRadius);
		}
		{
			JLabel lblOutCol = new JLabel("Outside Color:");
			lblOutCol.setBounds(10, 111, 94, 14);
			pnlCircle.add(lblOutCol);
		}
		{
			JLabel lblInsColor = new JLabel("Inside Color:");
			lblInsColor.setBounds(140, 111, 94, 14);
			pnlCircle.add(lblInsColor);
		}
		{
			txtXCenter = new JTextField();
			txtXCenter.setBounds(148, 30, 86, 20);
			pnlCircle.add(txtXCenter);
			txtXCenter.setColumns(10);
		}
		{
			txtYCenter = new JTextField();
			txtYCenter.setBounds(148, 55, 86, 20);
			pnlCircle.add(txtYCenter);
			txtYCenter.setColumns(10);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setBounds(148, 80, 86, 20);
			pnlCircle.add(txtRadius);
			txtRadius.setColumns(10);
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
			btnOutsideColor.setBounds(99, 111, 28, 28);
			pnlCircle.add(btnOutsideColor);
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
			btnInsideColor.setBounds(229, 111, 28, 28);
			pnlCircle.add(btnInsideColor);
		}
		{
			JLabel lblCenterCoordinates = new JLabel("Center coordinates");
			lblCenterCoordinates.setBounds(62, 11, 118, 14);
			pnlCircle.add(lblCenterCoordinates);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 163, 267, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{

				okButton.addActionListener(new ActionListener() {
					/**
					 * Method that confirms user choice
					 */
					public void actionPerformed(ActionEvent e) {
						xCenter = txtXCenter.getText();
						yCenter = txtYCenter.getText();
						radius = txtRadius.getText();
						
						if(xCenter.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(xCenter);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(yCenter.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert Y coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(yCenter);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(radius.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert radius.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(radius);

							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Radius must be number!", "Error", JOptionPane.ERROR_MESSAGE);

						}
						try {
							Integer.parseInt(xCenter);
							Integer.parseInt(yCenter);
							Integer.parseInt(radius);
							//repaint();
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
					 * Method for canceling a user choice
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
