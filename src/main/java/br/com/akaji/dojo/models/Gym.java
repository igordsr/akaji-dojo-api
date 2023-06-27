package br.com.akaji.dojo.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.akaji.dojo.dtos.GymDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gyms")
public class Gym implements Cast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ceo_id", nullable = false)
	private ChiefExecutiveOfficer ceo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", nullable = false)
	private Address headOfficeAddress;

	@OneToMany(mappedBy = "headOffice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Unit> units;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar created;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)

	private Calendar updated;

	@Column(columnDefinition = "boolean default false", nullable = false)
	protected Boolean deleted;

	public Gym(Long id, String name, String ceoName, String mainEmail, String secondaryEmail, String cellPhone,
			String landline, String zipCode, String logradouro, Long number, String complement, String referencePoint,
			String neighborhood, String city, String estate) {
		this.id = id;
		this.name = name;

		this.ceo = new ChiefExecutiveOfficer();
		this.headOfficeAddress = new Address();

		this.ceo.setName(ceoName);
		this.ceo.setMainEmail(mainEmail);
		this.ceo.setSecondaryEmail(secondaryEmail);
		this.ceo.setCellPhone(cellPhone);
		this.ceo.setLandline(landline);

		this.headOfficeAddress.setZipCode(zipCode);
		this.headOfficeAddress.setLogradouro(logradouro);
		this.headOfficeAddress.setNumber(number);
		this.headOfficeAddress.setComplement(complement);
		this.headOfficeAddress.setReferencePoint(referencePoint);
		this.headOfficeAddress.setNeighborhood(neighborhood);
		this.headOfficeAddress.setCity(city);
		this.headOfficeAddress.setEstate(estate);
	}

	@Override
	public DataTransferObject toDto() {
		return new GymDTO(this);
	}

}
