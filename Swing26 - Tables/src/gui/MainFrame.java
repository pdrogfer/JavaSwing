package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private JButton btn;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;

	public MainFrame() {
		super("Tables");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		btn = new JButton("Click me!");
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		
		controller = new Controller();
		
		tablePanel.setData(controller.getPeople());
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
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
			public void formEventOccurred(FormEvent ev) {

//				textPanel.appendText(name + ": " + occupation + ": "
//						+ ageCategory + ", " + empCat + ", " + gender + "\n");
	
				controller.addPerson(ev);
			}
		});

		add(toolbar, BorderLayout.NORTH);
		add(formPanel, BorderLayout.WEST);
		//add(textPanel, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);

		setSize(800, 500);
		// to avoid fields collapsing and disappearing on resizing
		setMinimumSize(new Dimension(500, 500));
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
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		/*
		 * Mnemonics and Accelerators are ways to increase accessibility and
		 * speed for power users. Mnemonics add a shortcut for menu items. By
		 * pressing Alt, a letter is underlined in the item or menu. They do not
		 * work in OS X.
		 */
		// Alt + F as shortcut for File menu
		fileMenu.setMnemonic(KeyEvent.VK_F);
		// Exit the app with a mnemonic
		exitFileMenu.setMnemonic(KeyEvent.VK_X);
		// Exit the app with an accelerator (Ctrl + X)
		exitFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				// this is the way to show the explorer to pickup a file:
				// fileChooser.showOpenDialog(MainFrame.this);
				// if a file is selected and click OK:
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
				
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// same as above, but for saving a file in a location
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		});
		
		exitFileMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* the INFORMATION_MESSAGE option sets a different icon.
				 * Explore also WARNING_MESSAGE, QUESTION_MESSAGE, and
				 * ERROR_MESSAGE 
				 */
				// the text entered can be retrieved through the variable textEntered
				String textEntered = JOptionPane.showInputDialog(MainFrame.this,
						"Enter your User ID", "User Authentication",
						JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE);

				// show a confirmation panel (remember Android?), retrieve the action
				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the app?", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		return menuBar;
	}
}
