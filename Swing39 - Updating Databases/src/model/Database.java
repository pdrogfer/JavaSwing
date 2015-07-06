package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
	private List<Person> people;
	private java.sql.Connection con;

	public Database() {
		/* linked lists are optimized to add and delete items
		 * anywhere in the list
		 */
		people = new LinkedList<Person>();
	}
	
	public void connect() throws Exception{
		
		if (con != null) {
			return; // do nothing if a connection is already open
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		
		String url = "jdbc:mysql://localhost:3306/swingtest";
		String user = "root";
		String password = "";
		
		con = DriverManager.getConnection(url, user, password);
		
		System.out.println("Connection to mySQL succesful and running");
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Can't close connection!");
			}
		} else {

		}
	}
	
	public void save() throws SQLException {
		
		// these strings are sql queries
		String checkSql = "select count(*) as count from people where id=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		
		String insertSql = "insert into people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updatetSql = "update people set name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updatetSql);

		for (Person person : people) {
			int id = person.getId();
			String name = person.getName();
			AgeCategory age = person.getAgeCategory();
			EmploymentCategory emp = person.getEmpCategory();
			String tax = person.getTaxId();
			boolean isUs = person.isUsCitizen();
			Gender gender = person.getGender();
			String occupation = person.getOccupation();
			
			checkStmt.setInt(1, id);
			
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			if (count == 0) {
				System.out.println("Inserting person with ID " + id);
				
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, emp.name());
				insertStatement.setString(col++, tax);
				insertStatement.setBoolean(col++, isUs);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occupation);
				
				insertStatement.executeUpdate();
				
			} else {
				System.out.println("Updating person with ID " + id);

				int col = 1;
				updateStatement.setString(col++, name);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, emp.name());
				updateStatement.setString(col++, tax);
				updateStatement.setBoolean(col++, isUs);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occupation);
				updateStatement.setInt(col++, id);
				
				updateStatement.executeUpdate();
			}
		}
		insertStatement.close();
		updateStatement.close();
		checkStmt.close();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
		
		oos.writeObject(persons);
		
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons = (Person[]) ois.readObject();
			
			people.clear();
			
			people.addAll(Arrays.asList(persons));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}

	public void removePerson(int index) {
		people.remove(index);
	}
	
}
