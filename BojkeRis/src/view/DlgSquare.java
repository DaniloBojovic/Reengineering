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
public class DlgSquare extends JDialog {

	private final JPanel pnlSquare = new JPanel();
	public JTextField txtXTopLeft;
	public JTextField txtYTopLeft;
	public JTextField txtSide;
	public String xTopLeft;
	public String yTopLeft;
	public String side;
	public Color outsideColor = (Color.black);
	public Color insideColor = (Color.white);
	public JButton btnOutsideColor = new JButton("");
	public JButton btnInsideColor = new JButton("");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSquare dialog = new DlgSquare(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSquare(Frame f) {
		super(f, true);
		setTitle("Square");
		setBounds(100, 100, 282, 244);
		getContentPane().setLayout(new BorderLayout());
		pnlSquare.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlSquare, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		pnlSquare.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Top left coordinates");
			lblNewLabel.setBounds(60, 11, 114, 14);
			pnlSquare.add(lblNewLabel);
		}
		{
			JLabel lblX = new JLabel("X:");
			lblX.setBounds(82, 36, 16, 14);
			pnlSquare.add(lblX);
		}
		{
			JLabel lblY = new JLabel("Y:");
			lblY.setBounds(82, 61, 16, 14);
			pnlSquare.add(lblY);
		}
		{
			JLabel lblSide = new JLabel("Side:");
			lblSide.setBounds(70, 92, 28, 14);
			pnlSquare.add(lblSide);
		}
		{
			JLabel lblInsCol = new JLabel("Inside Color:");
			lblInsCol.setBounds(152, 133, 78, 14);
			pnlSquare.add(lblInsCol);
		}
		{
			JLabel lblOutCol = new JLabel("Outside Color:");
			lblOutCol.setBounds(10, 133, 86, 14);
			pnlSquare.add(lblOutCol);
		}
		{
			txtXTopLeft = new JTextField();
			txtXTopLeft.setBounds(108, 33, 86, 20);
			pnlSquare.add(txtXTopLeft);
			txtXTopLeft.setColumns(10);
		}
		{
			txtYTopLeft = new JTextField();
			txtYTopLeft.setBounds(108, 61, 86, 20);
			pnlSquare.add(txtYTopLeft);
			txtYTopLeft.setColumns(10);
		}
		{
			txtSide = new JTextField();
			txtSide.setBounds(108, 89, 86, 20);
			pnlSquare.add(txtSide);
			txtSide.setColumns(10);
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
			btnInsideColor.setBounds(228, 133, 28, 28);
			pnlSquare.add(btnInsideColor);
		}
		{

			btnOutsideColor.addActionListener(new ActionListener() {
				/**
				 * Method used for choosing an outside color by JColorChooser
				 */
				public void actionPerformed(ActionEvent e) {
					outsideColor = JColorChooser.showDialog(null, "Choose color", outsideColor);
					btnOutsideColor.setBackground(outsideColor);
				}
			});
			btnOutsideColor.setBounds(98, 133, 28, 28);
			pnlSquare.add(btnOutsideColor);
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
						xTopLeft = txtXTopLeft.getText();
						yTopLeft = txtYTopLeft.getText();
						side = txtSide.getText();

						if(xTopLeft.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(xTopLeft);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(yTopLeft.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert Y coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(yTopLeft);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(side.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert the value for side.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(side);

							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Side must be number!", "Error", JOptionPane.ERROR_MESSAGE);

						}
						try {
							Integer.parseInt(xTopLeft);
							Integer.parseInt(yTopLeft);
							Integer.parseInt(side);
							repaint();
							setVisible(false);
						}

						catch (Exception e2) {

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
