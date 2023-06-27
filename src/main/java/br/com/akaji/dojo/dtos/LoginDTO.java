package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.common.Constants;
import br.com.akaji.dojo.interfaces.ValueObject;
import br.com.akaji.dojo.security.UserLogin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO implements ValueObject<UserLogin>{
	@NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
	@NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
	@NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
	protected String userName;

	@NotNull(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_NULL)
	@NotEmpty(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_EMPTY)
	@NotBlank(message = Constants.AKAJI_FORM_ERROR_HANDLER_REQUIRED_VALIDATION_FIELD_NOT_BLANCK)
	protected String password;

	@Override
	public UserLogin map() {
		UserLogin userLogin = new UserLogin();
		userLogin.setUserName(this.userName);
		userLogin.setPassword(this.password);
		return userLogin;
	}

	@Override
	public UserLogin map(UserLogin obj) {
		obj.setUserName(this.userName);
		obj.setPassword(this.password);
		return obj;
	}

	public UsernamePasswordAuthenticationToken toAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(this.userName, this.password);
	}

}
