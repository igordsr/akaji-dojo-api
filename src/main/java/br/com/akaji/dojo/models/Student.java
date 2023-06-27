package br.com.akaji.dojo.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.akaji.dojo.dtos.StudentDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User implements Cast {

    @Column(unique = true, updatable = false)
    private Long ra;
    private float bodyWeight;
    private float bodyHeight;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar startDate;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();


    public Student(Long id, Long ra, Calendar startDate, String name, Boolean enabled) {
        super.id = id;
        super.name = name;
        super.enabled = enabled;
        this.ra = ra;
        this.startDate = startDate;
    }

    public Student(User user) {
        super.id = user.getId();
        super.name = user.getName();
        super.socialName = user.getSocialName();
        super.rg = user.getRg();
        super.birthDate = user.getBirthDate();
        super.gender = user.getGender();
        super.mother = user.getMother();
        super.father = user.getFather();
        super.mainEmail = user.getMainEmail();
        super.secondaryEmail = user.getSecondaryEmail();
        super.cellPhone = user.getCellPhone();
        super.landline = user.getLandline();
        super.emergencyContact = user.getEmergencyContact();
        super.academicEducation = user.getAcademicEducation();
        super.academicEducationStatus = user.getAcademicEducationStatus();
        super.setProfession(user.getProfession());
        super.getAddress().setZipCode(user.getAddress().getZipCode());
        super.getAddress().setLogradouro((user.getAddress().getLogradouro()));
        super.getAddress().setNumber((user.getAddress().getNumber()));
        super.getAddress().setComplement((user.getAddress().getComplement()));
        super.getAddress().setReferencePoint((user.getAddress().getReferencePoint()));
        super.getAddress().setNeighborhood((user.getAddress().getNeighborhood()));
        super.getAddress().setCity((user.getAddress().getCity()));
        super.getAddress().setEstate((user.getAddress().getEstate()));
    }


    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public DataTransferObject toDto() {
        return new StudentDTO(this);
    }

}
