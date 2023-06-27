package br.com.akaji.dojo.controllers;


import br.com.akaji.dojo.configuration.security.TokenService;
import br.com.akaji.dojo.dtos.LoginDTO;
import br.com.akaji.dojo.dtos.TokenDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/auth")
class AuthenticationControllers {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginDTO loginVo, UriComponentsBuilder uriBuilder) {
        UsernamePasswordAuthenticationToken authenticationToken = loginVo.toAuthenticationToken();
        TokenDTO tokenDto = new TokenDTO();
        String token = null;
        try {
            Authentication authenticate = this.authManager.authenticate(authenticationToken);
            token = this.tokenService.generateToken(authenticate);
            tokenDto = new TokenDTO(token, "Bearer");
        } catch (AuthenticationException e) {
            log.error("authenticate error", e);
        }
        return ResponseEntity.ok(tokenDto);
    }
}
