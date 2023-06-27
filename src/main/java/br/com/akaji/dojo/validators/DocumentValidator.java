package br.com.akaji.dojo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.akaji.dojo.annotations.ValidateDocument;
import br.com.akaji.dojo.common.Functions;
import br.com.akaji.dojo.enums.Document;

public class DocumentValidator implements ConstraintValidator<ValidateDocument, String> {
	private Document document;

    @Override
    public void initialize(ValidateDocument validateDocument) {
    	this.document = validateDocument.value();
    }

	@Override
	public boolean isValid(String object, ConstraintValidatorContext context) {
        if (object == null) return true;
        
		Boolean valid = Boolean.FALSE;
		switch (this.document) {
		case RG:
			break;
		case CPF:
			valid = Functions.isCpfValid(object);
			break;
		case CNPJ:
			valid = Functions.isCnpjValid(object);
			break;
		default:
			break;
		}
		return object == null || object.isEmpty() || valid;
	}

}
