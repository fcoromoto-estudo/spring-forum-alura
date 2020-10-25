package br.com.fcoromoto.estudo.spring.springforumalura.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Getter
@Setter
public class LoginFormDTO {

    private String email;
    private String senha;


    public Authentication converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
