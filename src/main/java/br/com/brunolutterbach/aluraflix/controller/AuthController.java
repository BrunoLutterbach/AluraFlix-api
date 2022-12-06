package br.com.brunolutterbach.aluraflix.controller;


import br.com.brunolutterbach.aluraflix.dto.TokenDTO;
import br.com.brunolutterbach.aluraflix.dto.form.LoginForm;
import br.com.brunolutterbach.aluraflix.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authManager;
    TokenService tokenService;

    @PostMapping()
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(form.email(), form.senha());

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


