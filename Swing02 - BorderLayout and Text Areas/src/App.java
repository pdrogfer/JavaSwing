import javax.swing.SwingUtilities;


public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				// my class MainFrame extends JFrame
				// and I put all the gui there
				new MainFrame();
			}
		});
	}

}
