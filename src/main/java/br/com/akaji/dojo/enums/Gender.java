package br.com.akaji.dojo.enums;

public enum Gender {
	M("MALE"), 
	F("FEMELE");

	private String description;

	private Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
