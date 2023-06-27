package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.annotations.ValidateDocument;
import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.enums.AcademicEducationStatus;
import br.com.akaji.dojo.enums.Document;
import br.com.akaji.dojo.enums.Gender;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.interfaces.ValueObject;
import br.com.akaji.dojo.models.Student;
import br.com.akaji.dojo.models.User;
import br.com.akaji.dojo.repositories.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.orm.jpa.JpaSystemException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO implements DataTransferObject {

    protected Long id;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    @Size(min = 3, max = 255, message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_MIN_OR_MAX_VIOLATED)
    protected String name;

    protected String socialName;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String rg;

    @ValidateDocument(value = Document.CPF)
    @Size(min = 1, max = 15, message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_MIN_OR_MAX_VIOLATED)
    protected String cpf;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String birthDate;

    @NotEmpty
    @NotNull
    protected String gender;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String mother;

    protected String father;

    @Email(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_EMAIL_FIELD_INVALID)
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String mainEmail;

    protected String secondaryEmail;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String cellPhone;

    protected String landline;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String emergencyContact;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    protected @Valid AddressDTO address = new AddressDTO();

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    protected Integer academicEducation;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String academicEducationStatus;

    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    protected String profession;

    protected String userName;

    protected String password;
    protected Boolean enabled;


    public UserDTO(User user) {
        Gender gender = user.getGender();
        AcademicEducationStatus academicEducationStatus = user.getAcademicEducationStatus();

        this.id = user.getId();
        this.name = user.getName();
        this.socialName = user.getSocialName();
        this.rg = user.getRg();
        this.cpf = user.getCpf();
        this.birthDate = Functions.getDateInDefaultFormat(user.getBirthDate());
        this.gender = gender != null ? gender.name() : null;
        this.mother = user.getMother();
        this.father = user.getFather();
        this.mainEmail = user.getMainEmail();
        this.secondaryEmail = user.getSecondaryEmail();
        this.cellPhone = user.getCellPhone();
        this.landline = user.getLandline();
        this.emergencyContact = user.getEmergencyContact();
        this.academicEducation = user.getAcademicEducation();
        this.academicEducationStatus = academicEducationStatus != null ? academicEducationStatus.name() : null;
        this.profession = user.getProfession();
        this.getAddress().setZipCode(user.getAddress().getZipCode());
        this.getAddress().setLogradouro(user.getAddress().getLogradouro());
        this.getAddress().setNumber(user.getAddress().getNumber());
        this.getAddress().setComplement(user.getAddress().getComplement());
        this.getAddress().setReferencePoint(user.getAddress().getReferencePoint());
        this.getAddress().setNeighborhood(user.getAddress().getNeighborhood());
        this.getAddress().setCity(user.getAddress().getCity());
        this.getAddress().setEstate(user.getAddress().getEstate());
        this.enabled = user.getEnabled();
    }


    public User map() {
        return this.map(new User());
    }


    public User map(User user) {
        try {
            user.setId(user.getId());
            user.setName(this.name);
            user.setSocialName(this.socialName);
            user.setRg(this.rg);
            user.setBirthDate(Functions.formatDateInDefaultDateFormat(this.birthDate));
            user.setGender(Gender.valueOf(this.gender));
            user.setMother(this.mother);
            user.setFather(this.father);
            user.setMainEmail(this.mainEmail);
            user.setSecondaryEmail(this.secondaryEmail);
            user.setCellPhone(this.cellPhone);
            user.setLandline(this.landline);
            user.setEmergencyContact(this.emergencyContact);
            user.setProfession(this.profession);
            user.setAcademicEducation(this.academicEducation);
            user.setAcademicEducationStatus(AcademicEducationStatus.valueOf(this.academicEducationStatus));
            user.setAddress(this.address.map());
            user.setUpdated(Calendar.getInstance());
        } catch (JpaSystemException exception) {
            throw exception;
        } catch (Exception exception) {
            throw exception;
        }
        return user;
    }

    public Cast update(Long id, UserRepository userRepository) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUpdated(Calendar.getInstance());
            return this.map(user);
        }
        return new Student();
    }
}
