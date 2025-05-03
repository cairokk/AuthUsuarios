package br.unifor.usuario.controller;

import br.unifor.usuario.DTO.AuthResponseDTO;
import br.unifor.usuario.DTO.LoginRequestDTO;
import br.unifor.usuario.DTO.RegistrarRequestDTO;
import br.unifor.usuario.model.Usuario;
import br.unifor.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Autenticação", description = "Endpoints para login e registro de usuários")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Registrar novo usuário")
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody RegistrarRequestDTO dto) throws Exception {
        Usuario usuario = usuarioService.registrarUsuario(dto);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Autenticar e obter token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        AuthResponseDTO token = usuarioService.login(dto.getEmail(), dto.getSenha());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(summary = "Autenticar e obter token JWT")
    @GetMapping("/teste")
    public String teste() {
        return "deu certo oh";
    }


}

