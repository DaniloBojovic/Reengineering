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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
/**
 * Class DlgHexagonAdapter
 * @author Danilo
 *
 */
public class DlgHexagonAdapter extends JDialog {

	private final JPanel pnlHexagon = new JPanel();
	public JTextField txtX;
	public JTextField txtY;
	public JTextField txtRadius;
	public String xCenter;
	public String yCenter;
	public String radius;
	public Color outsideColor = (Color.black);
	public Color insideColor = (Color.white);
	public JButton btnInsideColor = new JButton("");
	public JButton btnOutsideColor = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagonAdapter dialog = new DlgHexagonAdapter(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagonAdapter(Frame f) {
		super(f, true);
		setTitle("Hexagon");
		setResizable(false);
		setBounds(100, 100, 290, 209);
		getContentPane().setLayout(new BorderLayout());
		pnlHexagon.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlHexagon, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		pnlHexagon.setLayout(null);
		{
			JLabel lblOutsideColor = new JLabel("Outside Color:");
			lblOutsideColor.setBounds(10, 109, 94, 14);
			pnlHexagon.add(lblOutsideColor);
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
			btnOutsideColor.setBounds(104, 109, 28, 28);
			pnlHexagon.add(btnOutsideColor);
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
			btnInsideColor.setBounds(221, 109, 28, 28);
			pnlHexagon.add(btnInsideColor);
		}
		{
			JLabel lblInsColor = new JLabel("Inside Color:");
			lblInsColor.setBounds(142, 111, 94, 14);
			pnlHexagon.add(lblInsColor);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setBounds(44, 82, 46, 14);
			pnlHexagon.add(lblRadius);
		}
		{
			JLabel lblY = new JLabel("Y:");
			lblY.setBounds(95, 54, 28, 14);
			pnlHexagon.add(lblY);
		}
		{
			JLabel lblX = new JLabel("X:");
			lblX.setBounds(95, 29, 28, 14);
			pnlHexagon.add(lblX);
		}
		{
			txtX = new JTextField();
			txtX.setColumns(10);
			txtX.setBounds(148, 29, 86, 20);
			pnlHexagon.add(txtX);
		}
		{
			txtY = new JTextField();
			txtY.setColumns(10);
			txtY.setBounds(148, 54, 86, 20);
			pnlHexagon.add(txtY);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setColumns(10);
			txtRadius.setBounds(148, 79, 86, 20);
			pnlHexagon.add(txtRadius);
		}
		{
			JLabel label = new JLabel("Center coordinates");
			label.setBounds(53, 7, 118, 14);
			pnlHexagon.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						xCenter = txtX.getText();
						yCenter = txtY.getText();
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
							repaint();
							setVisible(false);
						}

						catch (Exception ex) {

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
