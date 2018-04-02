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
/**
 * Class DlgLine
 * @author Danilo
 *
 */
public class DlgLine extends JDialog {

	private final JPanel pnlLine = new JPanel();
	public JTextField txtXStart;
	public JTextField txtYStart;
	public JTextField txtXEnd;
	public JTextField txtYEnd;
	public String xStart;
	public String yStart;
	public String xEnd;
	public String yEnd;
	public Color color = (Color.BLACK);
	public JButton btnColor = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine(Frame f) {
		super(f, true);
		setResizable(false);
		setTitle("Line");
		setBounds(100, 100, 248, 288);
		getContentPane().setLayout(new BorderLayout());
		pnlLine.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlLine, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pnlLine.setLayout(null);
		{
			JLabel lblStartPoint = new JLabel("Start point ");
			lblStartPoint.setBounds(84, 11, 66, 14);
			pnlLine.add(lblStartPoint);
		}
		{
			JLabel lblEndPoint = new JLabel("End point");
			lblEndPoint.setBounds(88, 91, 66, 14);
			pnlLine.add(lblEndPoint);
		}
		{
			JLabel lblXStart = new JLabel("X coordinate:");
			lblXStart.setBounds(10, 40, 76, 14);
			pnlLine.add(lblXStart);
		}
		{
			JLabel lblYStart = new JLabel("Y coordinate:");
			lblYStart.setBounds(10, 65, 76, 14);
			pnlLine.add(lblYStart);
		}
		{
			JLabel lblXEnd = new JLabel("X coordinate:");
			lblXEnd.setBounds(10, 123, 76, 14);
			pnlLine.add(lblXEnd);
		}
		{
			JLabel lblYEnd = new JLabel("Y coordinate:");
			lblYEnd.setBounds(10, 150, 76, 14);
			pnlLine.add(lblYEnd);
		}
		{
			JLabel lblcolor = new JLabel("Color:");
			lblcolor.setBounds(42, 189, 46, 14);
			pnlLine.add(lblcolor);
		}
		{
			txtXStart = new JTextField();
			txtXStart.setBounds(127, 36, 86, 20);
			pnlLine.add(txtXStart);
			txtXStart.setColumns(10);
		}
		{
			txtYStart = new JTextField();
			txtYStart.setBounds(127, 62, 86, 20);
			pnlLine.add(txtYStart);
			txtYStart.setColumns(10);
		}
		{
			txtXEnd = new JTextField();
			txtXEnd.setBounds(127, 120, 86, 20);
			pnlLine.add(txtXEnd);
			txtXEnd.setColumns(10);
		}
		{
			txtYEnd = new JTextField();
			txtYEnd.setBounds(127, 147, 86, 20);
			pnlLine.add(txtYEnd);
			txtYEnd.setColumns(10);
		}
		{

			btnColor.addActionListener(new ActionListener() {
				/**
				 * Method used for choosing a color from JColorChooser
				 */
				public void actionPerformed(ActionEvent arg0) {
					color = JColorChooser.showDialog(null, "Choose color", color);
					btnColor.setBackground(color);
				}
			});
			btnColor.setBounds(129, 189, 28, 28);
			pnlLine.add(btnColor);
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
						xStart = txtXStart.getText();
						yStart= txtYStart.getText();
						xEnd = txtXEnd.getText();
						yEnd= txtYEnd.getText();

						if(xStart.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(xStart);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(yStart.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert Y coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(yStart);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(xEnd.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(xEnd);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(yEnd.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert Y coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(yEnd);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}


						try {
							Integer.parseInt(xStart);
							Integer.parseInt(yStart);
							Integer.parseInt(xEnd);
							Integer.parseInt(yEnd);
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
					 * Method used for canceling a user choice
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
