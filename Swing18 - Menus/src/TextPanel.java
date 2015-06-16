import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea textArea;

	public TextPanel() {
			
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		// wrap the textArea in a JScrollPane to have scroll bars
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String text) {
		
		textArea.append(text);
	}
}
