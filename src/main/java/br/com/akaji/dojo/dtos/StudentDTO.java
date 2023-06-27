package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.interfaces.ValueObject;
import br.com.akaji.dojo.models.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = true)
@NoArgsConstructor
public final class StudentDTO extends UserDTO implements ValueObject<Student> {
    private Long ra;
    private float bodyWeight;
    private float bodyHeight;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String startDate;


    public StudentDTO(Student student) {
        super(student);
        this.ra = student.getRa();
        this.bodyWeight = student.getBodyWeight();
        this.bodyHeight = student.getBodyHeight();
        this.startDate = Functions.getDateInDefaultFormat(student.getStartDate());
    }

    @Override
    public Student map() {
        return map(new Student());
    }

    @Override
    public Student map(Student student) {
        if (student == null) {
            student = new Student();
        }
        super.map(student);
        student.setRa(this.ra);
        student.setBodyWeight(this.bodyWeight);
        student.setBodyHeight(this.bodyHeight);
        student.setStartDate(Functions.formatDateInDefaultDateFormat(this.startDate));
        return student;
    }
}
