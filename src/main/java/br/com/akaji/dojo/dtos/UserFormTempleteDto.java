package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.models.AcademicEducation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserFormTempleteDto {
    private List<AcademicEducation> academicEducationOptions;
}
