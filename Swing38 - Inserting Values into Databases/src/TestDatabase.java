import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test");
		
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Paul Newman", AgeCategory.adult, EmploymentCategory.unemployed, "441", true, Gender.male, "actor"));
		db.addPerson(new Person("Betty Davis", AgeCategory.senior, EmploymentCategory.unemployed, null, false, Gender.female, "actress"));
		
		try {
			db.save();
			System.out.println("saved to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnect();
	}

}
