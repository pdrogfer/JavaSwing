package model;

import java.io.Serializable;

/*
 * the classes in this 'model' package may NEVER EVER use directly components
 * from classes in the 'gui' package
 */
public class Person implements Serializable {
	
	private static final long serialVersionUID = 7568745478585432226L;
	private static int count = 1;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empCategory;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;
	
	public Person(String name, AgeCategory ageCategory, EmploymentCategory empCategory,
			String taxId, boolean usCitizen, Gender gender, String occupation) {
		this.name = name;
		this.ageCategory = ageCategory;
		this.empCategory = empCategory;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
		this.occupation = occupation;
		
		this.id = count;
		count++;
	}

	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentCategory getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(EmploymentCategory empCategory) {
		this.empCategory = empCategory;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
