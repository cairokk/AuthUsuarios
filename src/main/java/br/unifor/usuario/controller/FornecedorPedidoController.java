package br.unifor.usuario.controller;

import br.unifor.usuario.DTO.PedidoDTO;
import br.unifor.usuario.enums.StatusPedido;
import br.unifor.usuario.service.JwtService;
import br.unifor.usuario.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor/pedidos")
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
    @Operation(summary = "Atualiza o status do pedido", security = @SecurityRequirement(name = "BearerAuth"))
    @PutMapping("/{idPedido}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long idPedido, @RequestParam StatusPedido status,
                                             HttpServletRequest request) {


        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT inválido ou ausente.");
        }
        String token = authHeader.replace("Bearer ", "").trim(); // remove "Bearer "


        Long fornecedorId = jwtService.extractId(token); // claim "id" no token

        pedidoService.atualizarStatus(idPedido, status, fornecedorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/debug-role1")
    public ResponseEntity<?> debug(Authentication auth) {
        return ResponseEntity.ok(auth.getAuthorities());
    }
}

