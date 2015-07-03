package controller;

import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

/*
 * the MainFrame will call this class when it wants to add or
 * access data stored in the model. As can be seen it needs to import
 * gui.FormEvent to handle data provided by the gui, this is not so bad
 * in here, but gui stuff must never reach the model
 */
public class Controller {
	
	private Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}

	public void addPerson(FormEvent ev) {
		
		String name = ev.getName();
		int ageCat = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		String taxId = ev.getTaxID();
		boolean isUsCitizen = ev.isUsCitizen();
		String gender = ev.getGender();
		String occupation = ev.getOccupation();
		
		/*
		 * the next blocks are necessary to cast values before adding them 
		 * to the constructor below
		 */
		
		AgeCategory ageCategory = null;
		switch (ageCat) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		}
		
		EmploymentCategory empCategory;
		if(empCat.equals("employed")) {
			empCategory = EmploymentCategory.employed;
		} else if (empCat.equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
		} else if (empCat.equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmployed;
		} else {
			empCategory = EmploymentCategory.other;
		}
		
		Gender genderCat;
		if (gender == "male") {
			genderCat = Gender.male;
		} else genderCat = Gender.female;
		
		Person person = new Person(name, ageCategory, empCategory, taxId, isUsCitizen, genderCat, occupation);
		db.addPerson(person);
		System.out.println("person added to database");
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
	
	
	
	
	
	
	
	
	
}
