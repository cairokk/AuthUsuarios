package br.unifor.usuario.service;


import br.unifor.usuario.model.Usuario;
import br.unifor.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.unifor.usuario.DTO.AuthResponseDTO;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getRole())
                .build();
    }

    public AuthResponseDTO login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(email);
        return new AuthResponseDTO(token);
    }


    public Usuario registrarUsuario(String email, String senha, String role) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(role);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> autenticarUsuario(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent() &&
                passwordEncoder.matches(senha, usuarioOpt.get().getSenha())) {
            return usuarioOpt;
        }

        return Optional.empty();
    }

}
