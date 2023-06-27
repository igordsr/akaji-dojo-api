package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Gym;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GymDTO implements DataTransferObject {

    private Long id;

    private String name;

    private DataTransferObject ceo;

    private DataTransferObject headOfficeAddress;

    public GymDTO(Gym gym) {
        this.id = gym.getId();
        this.name = gym.getName();
        this.ceo = gym.getCeo().toDto();
        this.headOfficeAddress = gym.getHeadOfficeAddress().toDto();
    }
}
