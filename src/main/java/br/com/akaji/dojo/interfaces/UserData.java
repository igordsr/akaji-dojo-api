package br.com.akaji.dojo.interfaces;

import java.util.Calendar;

import br.com.akaji.dojo.enums.AcademicEducationStatus;
import br.com.akaji.dojo.enums.Gender;
import br.com.akaji.dojo.models.AcademicEducation;
import br.com.akaji.dojo.models.Address;

public interface UserData {
	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

	public String getSocialName();

	public void setSocialName(String socialName);

	public String getRg();

	public void setRg(String rg);

	public Calendar getBirthDate();

	public void setBirthDate(Calendar birthDate);

	public Gender getGender();

	public void setGender(Gender gender);

	public String getMother();

	public void setMother(String mother);

	public String getFather();

	public void setFather(String father);

	public String getMainEmail();

	public void setMainEmail(String mainEmail);

	public String getSecondaryEmail();

	public void setSecondaryEmail(String secondaryEmail);

	public String getCellPhone();

	public void setCellPhone(String cellPhone);

	public String getLandline();

	public void setLandline(String landline);

	public Address getAddress();

	public void setAddress(Address address);

	public AcademicEducation getAcademicEducation();

	public void setAcademicEducation(AcademicEducation academicEducation);

	public AcademicEducationStatus getAcademicEducationStatus();

	public void setAcademicEducationStatus(AcademicEducationStatus academicEducationStatus);

	public String getProfession();

	public void setProfession(String profession);
}
