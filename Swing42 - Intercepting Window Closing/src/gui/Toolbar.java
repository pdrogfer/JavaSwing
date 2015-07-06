package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Toolbar extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton saveBtn;
	private JButton refreshBtn;
	
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		// set the border type, using a static method
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		saveBtn = new JButton("Save");
		refreshBtn = new JButton("Refresh");
		
		saveBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(saveBtn);
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
