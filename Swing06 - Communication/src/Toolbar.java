import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton helloBtn;
	private JButton goodbyeBtn;
	
	
	// Bad Implementation: the Toolbar and the TextPanel are not
	// fully separated
	private TextPanel textPanel;
	
	public Toolbar() {
		helloBtn = new JButton("hello");
		goodbyeBtn = new JButton("goodbye");
		
		helloBtn.addActionListener(this);
		goodbyeBtn.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(helloBtn);
		add(goodbyeBtn);
	}

	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton btnClicked = (JButton)e.getSource();
		
		if (btnClicked == helloBtn) {
			textPanel.appendText("button hello\n");
		} else if (btnClicked == goodbyeBtn) {
			textPanel.appendText("button goodbye\n");
		}
	}
}
