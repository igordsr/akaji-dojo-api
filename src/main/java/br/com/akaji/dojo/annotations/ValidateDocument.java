package br.com.akaji.dojo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.enums.Document;
import br.com.akaji.dojo.validators.DocumentValidator;

@Documented
@Constraint(validatedBy = DocumentValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateDocument {
	public String message() default Constants.AKAJI_CONSTRAINT_VALIDATOR_ANNOTATION_INVALID_DOCUMENT;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Document value();
}