import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class FormPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	
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
		
		/* each graphic element can have a border, with setBorder. In this case, the
		 * static method compoundBorder takes two border objects as parameters
		 */
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
	}
}
