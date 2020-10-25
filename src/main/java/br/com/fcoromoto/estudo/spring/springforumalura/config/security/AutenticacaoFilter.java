package br.com.fcoromoto.estudo.spring.springforumalura.config.security;

import br.com.fcoromoto.estudo.spring.springforumalura.modelo.Usuario;
import br.com.fcoromoto.estudo.spring.springforumalura.repository.UsuarioRepository;
import br.com.fcoromoto.estudo.spring.springforumalura.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {


    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;


    public AutenticacaoFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        boolean isTokenValido = tokenService.isValido(token);

        if(isTokenValido){
            autenticar(token);
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void autenticar(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario principal = usuarioRepository.findById(idUsuario).orElseThrow(NullPointerException::new);


        Authentication autentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autentication);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String autorization = httpServletRequest.getHeader("Authorization");

        if(autorization == null || autorization.isEmpty() || ! autorization.startsWith("Bearer ")){
            return null;
        }
        return autorization.substring(7, autorization.length());
    }
}
