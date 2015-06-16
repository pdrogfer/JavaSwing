import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel {

	private JButton helloBtn;
	private JButton goodbyeBtn;
	
	public Toolbar() {
		helloBtn = new JButton("hello");
		goodbyeBtn = new JButton("goodbye");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(helloBtn);
		add(goodbyeBtn);
		
		
		
	}

	
}
