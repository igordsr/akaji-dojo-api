package br.com.akaji.dojo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentFormTempleteDto extends UserFormTempleteDto{

	public StudentFormTempleteDto(UserFormTempleteDto userFormTempleteDto) {
		super.setAcademicEducationOptions(userFormTempleteDto.getAcademicEducationOptions());
	}

	
}
