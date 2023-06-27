package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.ChiefExecutiveOfficer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChiefExecutiveOfficerDTO extends UserDTO implements DataTransferObject {
	public ChiefExecutiveOfficerDTO(ChiefExecutiveOfficer chiefExecutiveOfficer) {
		super(chiefExecutiveOfficer);
	}

}
