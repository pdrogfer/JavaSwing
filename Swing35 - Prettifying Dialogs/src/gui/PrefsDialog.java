package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class PrefsDialog extends JDialog{

	private static final long serialVersionUID = -1519866157924847680L;
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	
	private PrefsListener prefsListener;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);

		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		// layout specifics setup encapsulated for easy of reading
		layoutControls();

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer port = (Integer) portSpinner.getValue();
				
				String user = userField.getText();
				
				// JPasswordField is an array of char, need to parse to get the String
				String password = new String(passField.getPassword());
				
				if (prefsListener != null) {
					prefsListener.preferencesSet(user, password, port);
				}
				
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

		setSize(350, 250);
		// show in the center of the screen
		setLocationRelativeTo(parent);

	}
	
	private void layoutControls() {
		
		/* one upper panel for controls and 
		 * another at the bottom for the buttons
		 */
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		//borders setup
		int space= 7;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		controlsPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		
		Insets rightPadding = new Insets(0, 0, 0, 5); // some left margin between labels and fields
		Insets noPadding = new Insets(0, 0, 0, 0);
		
		// ///////////////// first row ////////////////////////
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		
		gc.gridx = 0;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("User: "), gc);
		
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(userField, gc);
		
		//////////////////// next row ////////////////////////
		gc.gridy++;
		gc.anchor = GridBagConstraints.EAST;
		
		gc.gridx = 0;
		
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Password: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;

		controlsPanel.add(passField, gc);
		
		//////////////////// next row ////////////////////////
		gc.gridy++;
		gc.anchor = GridBagConstraints.EAST;

		gc.gridx = 0;

		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Port: "), gc);

		gc.gridx++;
		gc.insets = noPadding;
		gc.anchor = GridBagConstraints.WEST;

		controlsPanel.add(portSpinner, gc);

		//////////////////// Buttons Panel ////////////////////////
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton, gc);
		buttonsPanel.add(cancelButton, gc);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		// Add both controls and buttons panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void setDefaults(String user, String password, int port) {
		userField.setText(user);
		passField.setText(password);
		portSpinner.setValue(port);
	}

	public void setPrefsListener(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;
	}

}
