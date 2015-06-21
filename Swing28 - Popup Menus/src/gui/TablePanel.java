package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;
import model.PersonTableModel;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 2107307346724761694L;
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	
	public TablePanel() {
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		
		// show this message as a popup when right-click
		JMenuItem removeItem = new JMenuItem("Delete Row");
		popup.add(removeItem);
		
		// simple implementation, instead of: new MouseListener...
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
