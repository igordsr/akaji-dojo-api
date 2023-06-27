package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HealthInfoDTO implements DataTransferObject {
    private Long id;
    private DataTransferObject student;
}
