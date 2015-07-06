package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private PersonTableListener personTableListener;
	
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
				
				//to select one row, first find out which row the mouse has clicked
				//careful, first row is row 0.
				int row = table.rowAtPoint(e.getPoint());
				//select a section of the table starting and ending at the row selected
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(personTableListener != null) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}
	
	public void setPersonTableListener(PersonTableListener listener) {
		this.personTableListener = listener;
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
