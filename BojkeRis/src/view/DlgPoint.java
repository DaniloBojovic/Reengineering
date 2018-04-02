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
 * Class DlgPoint
 * @author Danilo
 *
 */
public class DlgPoint extends JDialog {

	private final JPanel pnlPoint = new JPanel();
	public JTextField txtX;
	public JTextField txtY;
	public String x;
	public String y;
	public Color color = (Color.black);
	public JButton btnColor = new JButton("");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint(Frame f) {
		super(f, true);
		setResizable(false);
		setTitle("Point");
		setBounds(100, 100, 302, 159);
		getContentPane().setLayout(new BorderLayout());
		pnlPoint.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlPoint, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pnlPoint.setLayout(null);
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setBounds(47, 14, 82, 14);
			pnlPoint.add(lblX);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			lblY.setBounds(47, 40, 82, 14);
			pnlPoint.add(lblY);
		}
		
		txtX = new JTextField();
		txtX.setBounds(139, 11, 86, 20);
		pnlPoint.add(txtX);
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setBounds(139, 37, 86, 20);
		pnlPoint.add(txtY);
		txtY.setColumns(10);
		{
			JLabel lblColor = new JLabel("Color:");
			lblColor.setBounds(47, 65, 46, 14);
			pnlPoint.add(lblColor);
		}
				
		btnColor.addActionListener(new ActionListener() {
			/**
			 * Method used for choosing a color from JColorChooser
			 */
			public void actionPerformed(ActionEvent arg0) {
				color = JColorChooser.showDialog(null, "Choose color", color);			
				btnColor.setBackground(color);				
			}
		});
		btnColor.setBounds(139, 61, 28, 28);
		pnlPoint.add(btnColor);
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
						x = txtX.getText();
						y = txtY.getText();

						if(x.length() < 1){

							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(x);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						if(y.length() < 1){
							JOptionPane.showMessageDialog(null, "This field is mandatory! \nPlease insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(y);
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}

						try {
							Integer.parseInt(x);
							Integer.parseInt(y);
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
					 * Method used for canceling user choice
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
