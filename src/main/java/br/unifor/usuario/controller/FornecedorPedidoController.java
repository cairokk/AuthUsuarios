package br.unifor.usuario.controller;

import br.unifor.usuario.DTO.PedidoDTO;
import br.unifor.usuario.enums.StatusPedido;
import br.unifor.usuario.service.JwtService;
import br.unifor.usuario.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor/pedidos")
@PreAuthorize("hasRole('FORNECEDOR')")
public class FornecedorPedidoController {

    @Autowired
    private PedidoService pedidoService;

   @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidosDoFornecedor(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // remove "Bearer "
        Long fornecedorId = jwtService.extractId(token); // claim "id" no token
        List<PedidoDTO> pedidos = pedidoService.listarPedidosDoFornecedor(fornecedorId);
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{idPedido}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long idPedido, @RequestParam StatusPedido status,
                                             HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // remove "Bearer "
        Long fornecedorId = jwtService.extractId(token); // claim "id" no token

        pedidoService.atualizarStatus(idPedido, status, fornecedorId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Autenticar e obter token JWT")
    @GetMapping("/teste")
    public String teste() {
        return "deu certo oh";
    }
}

