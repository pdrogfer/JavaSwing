import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	
	public FormPanel() {
		//each component has a default size
		Dimension dim = getPreferredSize();
		
		// set the size of the formPanel
		dim.width = 250;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		// 10 is the default width in characters
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		okBtn = new JButton("OK");
		
		/* when okBtn is clicked, submit the text entered
		 * in the other 2 fields
		 */
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				FormEvent ev = new FormEvent(this, name, occupation);
				
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});
		
		
		
		/* each graphic element can have a border, with setBorder. In this case, the
		 * static method compoundBorder takes two border objects as parameters
		 */
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////////////// First Row //////////////////////////////////
		/* as in Android, the weight dictates the relative amount of space each element
		 * takes in relation with the others.
		 */
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		// the anchor method positions the content "inside" it's container
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		///////////////////// Second Row //////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///////////////////// Third Row //////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 8;
		gc.gridx = 1;
		gc.gridy = 2;
		// at the top-left corner of it's container
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	

}
