package br.com.akaji.dojo.enums;

public enum AcademicEducationStatus {
	INCOMPLETE("1"), 
	IN_PROGRESS("2"), 
	COMPLETED("3");

	private String description;

	private AcademicEducationStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
