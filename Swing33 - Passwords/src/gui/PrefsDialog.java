package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog {

	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);

		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ///////////////// first row ////////////////////////
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		
		add(new JLabel("User: "), gc);
		
		gc.gridx++;
		add(userField, gc);
		
		//////////////////// next row ////////////////////////
		gc.gridy++;
		
		gc.gridx = 0;
		
		add(new JLabel("Password: "), gc);
		
		gc.gridx++;
		add(passField, gc);
		
		//////////////////// next row ////////////////////////
		gc.gridy++;

		gc.gridx = 0;

		add(new JLabel("Port: "), gc);

		gc.gridx++;
		add(portSpinner, gc);

		//////////////////// next row ////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		add(okButton, gc);

		gc.gridx++;
		add(cancelButton, gc);

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer value = (Integer) portSpinner.getValue();
				
				String user = userField.getText();
				
				// JPasswordField is an array of char, need to parse to get the String
				String password = new String(passField.getPassword());
				
				System.out.println(user + ": " + password);
				// make it dissapear after click
				setVisible(false);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		setSize(400, 300);
		// show in the center of the screen
		setLocationRelativeTo(parent);

	}

}
