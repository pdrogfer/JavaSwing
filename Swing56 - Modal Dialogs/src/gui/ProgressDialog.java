package gui;

import java.awt.Window;

import javax.swing.JDialog;

public class ProgressDialog extends JDialog {
	public ProgressDialog(Window window) {
		super (window, "Messages Downloading...", ModalityType.APPLICATION_MODAL);
		
		setSize(400, 300);
	}

}
