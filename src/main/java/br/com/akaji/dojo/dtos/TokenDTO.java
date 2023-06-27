package br.com.akaji.dojo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDTO {

	private String token;
	private String authenticationType;

	public TokenDTO(String token, String authenticationType) {
		this.token = token;
		this.authenticationType = authenticationType;
	}
}
