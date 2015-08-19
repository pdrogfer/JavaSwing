package gui;
import javax.swing.SwingUtilities;


/*
 * This tutorial is about creating executable files. There is two options:
 * - generate a .jar file (2º button over Application -- Export -- Java --
 * Runnable .jar file -- "Package required libraries into generated jar."
 * 
 * - the same as above, and then download "jsmooth", a program to convert 
 * .jar files into .exe, typical windows executable files that people is used
 * to.
 */
public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

}
