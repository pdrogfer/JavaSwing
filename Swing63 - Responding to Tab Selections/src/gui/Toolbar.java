package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class Toolbar extends JToolBar implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton saveBtn;
	private JButton refreshBtn;
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		// removing the border makes the toolbar draggable
		// setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		// to prevent it from float
		// setFloatable(false);
		
		saveBtn = new JButton("Save");
		refreshBtn = new JButton("Refresh");
		
		saveBtn.setIcon(Utils.createIcon("/images/Save16.gif"));
		refreshBtn.setIcon(Utils.createIcon("/images/Refresh16.gif"));
		
		saveBtn.setToolTipText("Save");
		refreshBtn.setToolTipText("Refresh");
		
		saveBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		
		add(saveBtn);
		//addSeparator();
		add(refreshBtn);
	}

	public void setToolbarListener(ToolbarListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton btnClicked = (JButton)e.getSource();
		
		if (btnClicked == saveBtn) {
			if (textListener != null) {
				textListener.saveEventOccurred();
			}
		} else if (btnClicked == refreshBtn) {
			if (textListener != null) {
				textListener.refreshEventOccurred();
			}
			
		}
	}
}
