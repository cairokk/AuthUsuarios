package br.unifor.usuario.service;


import br.unifor.usuario.DTO.RegistrarRequestDTO;
import br.unifor.usuario.model.Usuario;
import br.unifor.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.unifor.usuario.DTO.AuthResponseDTO;

import java.util.UUID;
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

        String token = jwtService.generateToken(email, usuario.getId(), usuario.getRole());
        return new AuthResponseDTO(token);
    }


    public Usuario registrarUsuario(RegistrarRequestDTO dto) throws Exception {

        if (dto == null) { throw new Exception("Dados não recebidos!");}

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (dto.getEmail() == null) {throw new Exception("Email é obrigatório!");}
        if (dto.getNome() == null) {throw new Exception("Nome é obrigatório!");}
        if (dto.getSobrenome() == null) {throw new Exception("Sobrenome é obrigatório!");}
        if (dto.getSenha() == null) {throw new Exception("Senha é obrigatório!");}
        if (dto.getCpf() == null) {throw new Exception("Cpf é obrigatório!");}
        if (dto.getTelefone() == null) {throw new Exception("Numero de celular é obrigatório!");}

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(dto.getRole());
        usuario.setCpf(dto.getCpf());
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setTelefone(dto.getTelefone());

        if (usuario.getRole() == null) {
            usuario.setRole("cliente");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

//    public Optional<Usuario> autenticarUsuario(String email, String senha) {
//        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
//
//        if (usuarioOpt.isPresent() &&
//                passwordEncoder.matches(senha, usuarioOpt.get().getSenha())) {
//            return usuarioOpt;
//        }
//
//        return Optional.empty();
//    }

}
