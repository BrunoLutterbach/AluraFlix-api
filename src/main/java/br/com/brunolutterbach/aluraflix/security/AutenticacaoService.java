package br.com.brunolutterbach.aluraflix.security;

import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário " + username + " não encontrado"));
    }
}
