package br.com.akaji.dojo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.akaji.dojo.annotations.ValidateDocument;
import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.enums.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentInfoDTO {
	@NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
	@NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
	@NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
	@Size(min = 3, max = 255, message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_MIN_OR_MAX_VIOLATED)
	protected String name;

	@NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
	@NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
	@NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
	protected String rg;

	@ValidateDocument(value = Document.CPF)
	@Size(min = 1, max = 15, message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_MIN_OR_MAX_VIOLATED)
	protected String cpf;
}
