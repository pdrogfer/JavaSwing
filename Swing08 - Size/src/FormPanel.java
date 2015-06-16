import java.awt.Dimension;

import javax.swing.JPanel;


public class FormPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormPanel() {
		//each component has a default size
		Dimension dim = getPreferredSize();
		
		// this changes the size of the formPanel
		dim.width = 150;
		setPreferredSize(dim);
	}
}
