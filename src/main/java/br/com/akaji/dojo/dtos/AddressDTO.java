package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.interfaces.ValueObject;
import br.com.akaji.dojo.models.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO implements DataTransferObject, ValueObject<Address> {

    private Long id;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String zipCode;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String logradouro;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    private Long number;
    private String complement;
    private String referencePoint;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String neighborhood;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String city;
    @NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
    @NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
    @NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
    private String estate;

    public AddressDTO(Address address) {
        this.zipCode = address.getZipCode();
        this.logradouro = address.getLogradouro();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.referencePoint = address.getReferencePoint();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.estate = address.getEstate();
    }

    @Override
    public Address map() {
        return new Address(this);
    }

    @Override
    public Address map(Address obj) {
        return null;
    }
}
