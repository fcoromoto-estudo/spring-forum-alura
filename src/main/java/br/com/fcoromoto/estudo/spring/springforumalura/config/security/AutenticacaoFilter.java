package br.com.fcoromoto.estudo.spring.springforumalura.config.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);

        System.out.println(token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String autorization = httpServletRequest.getHeader("Authorization");

        if(autorization == null || autorization.isEmpty() || ! autorization.startsWith("Bearer ")){
            return null;
        }
        return autorization.substring(7, autorization.length());
    }
}
