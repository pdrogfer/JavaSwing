import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JButton btn;
	
	public MainFrame() {
		super("Hello World");
		
		setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		btn = new JButton("Click me!");
		
		add(textArea, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
