package br.com.akaji.dojo.models;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.dtos.AddressDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "addresses")
public class Address implements Cast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 15, nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private Long number;

	@Column(nullable = true)
	private String complement;

	@Column(nullable = true)
	private String referencePoint;

	@Column(nullable = false)
	private String neighborhood;

	@Column(length = 100, nullable = false)
	private String city;

	@Column(length = 100, nullable = false)
	private String estate;

	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> userData = new ArrayList<>();

	@OneToMany(mappedBy = "headOfficeAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Gym> gym = new ArrayList<>();

	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Unit> unit = new ArrayList<>();

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar created = Calendar.getInstance();

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Calendar updated;


	public Address(AddressDTO addressDTO){
		if(addressDTO == null){
			throw new IllegalArgumentException("AddressDTO".concat(Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL));
		}
		this.id = addressDTO.getId();
		this.zipCode = addressDTO.getZipCode();
		this.logradouro = addressDTO.getLogradouro();
		this.number = addressDTO.getNumber();
		this.complement = addressDTO.getComplement();
		this.referencePoint = addressDTO.getReferencePoint();
		this.neighborhood = addressDTO.getNeighborhood();
		this.city = addressDTO.getCity();
		this.estate = addressDTO.getEstate();
	}

	public Address(Long id, String zipCode, String logradouro, Long number, String complement, String referencePoint,
			String neighborhood, String city, String estate, Calendar created, Calendar updated) {
		this.id = id;
		this.zipCode = zipCode;
		this.logradouro = logradouro;
		this.number = number;
		this.complement = complement;
		this.referencePoint = referencePoint;
		this.neighborhood = neighborhood.toString();
		this.city = city;
		this.estate = estate;
		this.created = created;
		this.updated = updated;
	}

	@Override
	public DataTransferObject toDto() {
		return new AddressDTO(this);
	}

}
