import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
	private JList ageList;
	private JComboBox employmentCombo;
	
	public FormPanel() {
		//each component has a default size
		Dimension dim = getPreferredSize();
		
		// set the size of the formPanel
		dim.width = 320;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		// 10 is the default width in characters
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		okBtn = new JButton("OK");
		ageList = new JList();
		employmentCombo = new JComboBox();
		
		// creating the model for the list
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "over 65"));
		
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(122,  68));
		Border ageListBorder = BorderFactory.createEtchedBorder();
		ageList.setBorder(ageListBorder);
		// set default value
		ageList.setSelectedIndex(1);
		
		// creating the model for the combo
		DefaultComboBoxModel employmentModel = new DefaultComboBoxModel();
		employmentModel.addElement("employed");
		employmentModel.addElement("self-employed");
		employmentModel.addElement("unemployed");
		
		employmentCombo.setModel(employmentModel);
		employmentCombo.setSelectedIndex(0);
		
		/* when okBtn is clicked, submit the text entered
		 * in the other 2 fields
		 */
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String employmentCat = (String) employmentCombo.getSelectedItem();
				
				System.out.println(ageCat + ": id " + ageCat.getId());
				System.out.println(employmentCat);
				
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), employmentCat);
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
		
		layoutComponents();
	}
	
	public void layoutComponents() {

		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////////////// First Row //////////////////////////////////
		/* as in Android, the weight dictates the relative amount of space each element
		 * takes in relation with the others.
		 */
		gc.gridy = 0; // the rows
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		// the anchor method positions the content "inside" it's container
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		///////////////////// Second Row //////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///////////////////// Third Row //////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);
		
		///////////////////// Fourth Row //////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 4;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment: "), gc);

		gc.gridx = 1;
		// at the top-left corner of it's container
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(employmentCombo, gc);

		///////////////////// Fifth Row //////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 1;
		// at the top-left corner of it's container
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
		
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class AgeCategory {
	
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
	
	public int getId() {
		return id;
	}
	
	
}