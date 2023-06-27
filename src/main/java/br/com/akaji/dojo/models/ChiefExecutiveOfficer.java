package br.com.akaji.dojo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.akaji.dojo.dtos.ChiefExecutiveOfficerDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ceos")
@PrimaryKeyJoinColumn(name = "id")
public class ChiefExecutiveOfficer extends User implements Cast {

	@OneToMany(mappedBy = "ceo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Gym> gym;

	@Override
	public DataTransferObject toDto() {
		return new ChiefExecutiveOfficerDTO(this);
	}
}
