package br.com.brunolutterbach.aluraflix.controller;

import br.com.brunolutterbach.aluraflix.dto.UsuarioDTO;
import br.com.brunolutterbach.aluraflix.dto.form.UsuarioForm;
import br.com.brunolutterbach.aluraflix.exception.ResourceNotFoundException;
import br.com.brunolutterbach.aluraflix.model.Usuario;
import br.com.brunolutterbach.aluraflix.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UsuarioRepository usuarioRepository;

    @PostMapping()
    public ResponseEntity<UsuarioForm> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm) {
        if (usuarioRepository.findByEmail(usuarioForm.email()).isPresent()) {
            throw new ResourceNotFoundException("E-mail já cadastrado");
        }
        usuarioRepository.save(new Usuario(usuarioForm));
        return ResponseEntity.ok(usuarioForm);
    }

    @GetMapping()
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
