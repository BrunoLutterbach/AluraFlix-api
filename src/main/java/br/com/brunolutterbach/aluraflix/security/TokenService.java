package br.com.brunolutterbach.aluraflix.security;

import br.com.brunolutterbach.aluraflix.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Long.parseLong;

@Service
public class TokenService {

    @Value("${aluraflix.jwt.expiration}")
    private String expiration;

    @Value("${aluraflix.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API AluraFlix")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        return parseLong(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
    }
}

