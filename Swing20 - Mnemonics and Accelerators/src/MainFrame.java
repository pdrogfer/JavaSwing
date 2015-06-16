import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private JButton btn;
	private Toolbar toolbar;
	private FormPanel formPanel;

	public MainFrame() {
		super("Mnemonics and Accelerators");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		btn = new JButton("Click me!");
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		
		setJMenuBar(createMenuBar());

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
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar(); 
		
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		JMenuItem exportDataItem = new JMenuItem("Export data...");
		JMenuItem importDataItem = new JMenuItem("Import data...");
		JMenuItem exitFileMenu = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitFileMenu);
		
		JMenu showMenu = new JMenu("Show");
		// this checkbox sets the visibility of the whole form
		JMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");		
		showFormItem.setSelected(true);
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		/* Mnemonics and Accelerators are ways to increase accessibility and speed for power users.
		 * Mnemonics add a shortcut for menu items. By pressing Alt, a letter is underlined in the 
		 * item or menu. They do not work in OS X.
		 */
		// Alt + F as shortcut for File menu
		fileMenu.setMnemonic(KeyEvent.VK_F);
		// Exit the app with a mnemonic
		exitFileMenu.setMnemonic(KeyEvent.VK_X);
		// Exit the app with an accelerator (Ctrl + X)
		exitFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		exitFileMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		return menuBar;
	}
}



















