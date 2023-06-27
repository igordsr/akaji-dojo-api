package br.com.akaji.dojo.models;

import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.dtos.UserDTO;
import br.com.akaji.dojo.enums.AcademicEducationStatus;
import br.com.akaji.dojo.enums.Gender;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.security.UserLogin;
import br.com.akaji.dojo.dtos.DocumentInfoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = true)
    protected String socialName;

    @Column(length = 20, nullable = false, unique = true)
    protected String rg;

    @Column(length = 15, nullable = true, unique = true)
    protected String cpf;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    protected Calendar birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Gender gender;

    @Column(nullable = false)
    protected String mother;

    @Column(nullable = true)
    protected String father;

    @Column(length = 200, nullable = false)
    protected String mainEmail;

    @Column(length = 200, nullable = true)
    protected String secondaryEmail;

    @Column(length = 20, nullable = false)
    protected String cellPhone;

    @Column(length = 20, nullable = true)
    protected String landline;

    @Column(length = 20, nullable = false)
    protected String emergencyContact;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    protected Address address = new Address();

    @Column(nullable = false, length = 2)
    protected Integer academicEducation;

    @Column(nullable = false)
    protected AcademicEducationStatus academicEducationStatus;

    @Column(length = 100, nullable = false)
    protected String profession;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected UserLogin userLogin;

    @Column(columnDefinition = "boolean default false", nullable = false)
    protected Boolean enabled = Boolean.FALSE;

    @Column(columnDefinition = "boolean default false", nullable = false)
    protected Boolean deleted = Boolean.FALSE;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar created = Calendar.getInstance();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Calendar updated;

    public User(DocumentInfoDTO documentInfoDTO){
        this.name = documentInfoDTO.getName();
        this.rg = documentInfoDTO.getRg();
        this.cpf = documentInfoDTO.getCpf();
    }

    public User(UserDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.socialName = dto.getSocialName();
        this.rg = dto.getRg();
        this.cpf = dto.getCpf();
        this.birthDate = Functions.formatDateInDefaultDateFormat(dto.getBirthDate());
        this.gender = Gender.valueOf(dto.getGender());
        this.mother = dto.getMother();
        this.father = dto.getFather();
        this.mainEmail = dto.getMainEmail();
        this.secondaryEmail = dto.getSecondaryEmail();
        this.cellPhone = dto.getCellPhone();
        this.landline = dto.getLandline();
        this.emergencyContact = dto.getEmergencyContact();
        this.academicEducation = dto.getAcademicEducation();
        this.academicEducationStatus = AcademicEducationStatus.valueOf(dto.getAcademicEducationStatus());
        this.profession = dto.getProfession();
        this.address = new Address();
        this.getAddress().setZipCode(dto.getAddress().getZipCode());
        this.getAddress().setLogradouro(dto.getAddress().getLogradouro());
        this.getAddress().setNumber(dto.getAddress().getNumber());
        this.getAddress().setComplement(dto.getAddress().getComplement());
        this.getAddress().setReferencePoint(dto.getAddress().getReferencePoint());
        this.getAddress().setNeighborhood(dto.getAddress().getNeighborhood());
        this.getAddress().setCity(dto.getAddress().getCity());
        this.getAddress().setEstate(dto.getAddress().getEstate());
    }

    public User(Long id, Boolean enabled, UserLogin userLogin) {
        this.id = id;
        this.enabled = enabled;
        this.userLogin = userLogin;
        this.address = new Address();
    }

    public User(Long id, String name, String socialName, String rg, String cpf, Calendar birthDate, Gender gender, String mother,
                String father, String mainEmail, String secondaryEmail, String cellPhone, String landline,
                String emergencyContact, Integer academicEducation, AcademicEducationStatus academicEducationStatus,
                String profession, Calendar created, Calendar updated, Long idAdddress, String zipCode, String logradouro,
                Long number, String complement, String referencePoint, String neighborhood, String city, String estate,
                Calendar createdAdddress, Calendar updatedAdddress) {
        this.id = id;
        this.name = name;
        this.socialName = socialName;
        this.rg = rg;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        this.mainEmail = mainEmail;
        this.secondaryEmail = secondaryEmail;
        this.cellPhone = cellPhone;
        this.landline = landline;
        this.emergencyContact = emergencyContact;
        this.address = new Address(idAdddress, zipCode, logradouro, number, complement, referencePoint, neighborhood,
                city, estate, createdAdddress, updatedAdddress);
        this.academicEducation = academicEducation;
        this.academicEducationStatus = academicEducationStatus;
        this.profession = profession;
        this.created = created;
        this.updated = updated;
        this.userLogin = new UserLogin();
    }

    @Override
    public DataTransferObject toDto() {
        return new UserDTO(this);
    }

}
