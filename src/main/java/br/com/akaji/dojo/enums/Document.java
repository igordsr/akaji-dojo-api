package br.com.akaji.dojo.enums;

public enum Document {
	CNPJ("CNPJ"),
	CPF("CPF"),
	RG("RG"); 

	private String description;

	private Document(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
