package davidemancini.U5_W3_D5.security;

import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.MyUnauthorizedException;
import davidemancini.U5_W3_D5.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");//RECUPERTO DALLA RICHIESTA L'HEADER AUTHORIZATIONE CHE CONTERRA IL BEARER TOKEN
        if (header == null || !header.startsWith("Bearer ")) { //SE L'HEADER è NULL OPPURE NON INIZIA CON BEARER  LANCIO UN ERRORE
            throw new MyUnauthorizedException("inserire un token valido");
        }
        String token = header.replace("Bearer ", ""); //QUI RIMPIAZZO "BEARER " CON STRINGA VUOTA COSI CHE MI RIMANE SOLO IL TOKEN EFFFETTIVO
        jwtTools.verificaToken(token);//VERIFICA DEL TOKEN

        UUID userId = jwtTools.idFromToken(token);
        User trovato = userService.findById(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(trovato, null, trovato.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) { //TUTTE LE RICHIESTE SU /AUTH NON VERRANNO FILTRATE
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
