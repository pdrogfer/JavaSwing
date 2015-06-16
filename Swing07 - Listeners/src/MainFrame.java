import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private JButton btn;
	private Toolbar toolbar;
	
	public MainFrame() {
		super("Listeners");
		
		setLayout(new BorderLayout());
		
		toolbar = new Toolbar();
		btn = new JButton("Click me!");
		textPanel = new TextPanel();
		
		toolbar.setStringListener(new StringListener() {
			
			@Override
			public void textEmitted(String text) {
				// override the method set up in the custom interface
				System.out.println(text);
				textPanel.appendText(text);
			}
		});
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textPanel.appendText("Button clicked again\n");
				
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
