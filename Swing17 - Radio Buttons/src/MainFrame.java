import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private JButton btn;
	private Toolbar toolbar;
	private FormPanel formPanel;

	public MainFrame() {
		super("Radio Buttons");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		btn = new JButton("Click me!");
		textPanel = new TextPanel();
		formPanel = new FormPanel();

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

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCategory = e.getAgeCategory();
				String empCat = e.getEmploymentCategory();
				String gender = e.getGender();
				textPanel.appendText(name + ": " + occupation + ": "
						+ ageCategory + ", " + empCat + ", " + gender + "\n");
			}
		});

		add(toolbar, BorderLayout.NORTH);
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);

		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
