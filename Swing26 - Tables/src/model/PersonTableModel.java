package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> db;
	private String[] colNames = { "ID", "Name", "Occupation", "Age Category",
			"Employment Category", "US Citizen", "Tax ID" };

	public PersonTableModel() {

	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getRowCount() {
		// number of items in the list is equal to the rows
		return db.size();
	}

	@Override
	public int getColumnCount() {
		// number of fields in each person object
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Person person = db.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmpCategory();
		case 5:
			return person.isUsCitizen();
		case 6:
			return person.getTaxId();
		}
		// this should never happen
		return null;
	}
}
