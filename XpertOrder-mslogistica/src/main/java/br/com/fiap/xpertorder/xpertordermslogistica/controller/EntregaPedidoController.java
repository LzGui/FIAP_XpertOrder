package br.com.fiap.xpertorder.xpertordermslogistica.controller;

import br.com.fiap.xpertorder.xpertordermslogistica.model.EntregaPedido;
import br.com.fiap.xpertorder.xpertordermslogistica.service.EntregaPedidoService;
import com.google.maps.model.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaPedidoController {

   @Autowired
   private EntregaPedidoService entregaPedidoService;

   @GetMapping
   public List<EntregaPedido> obterTodasEntregas(){
      return entregaPedidoService.obterTodasEntregas();
   }

   @GetMapping("/{id}")
   public ResponseEntity<EntregaPedido> getPedidoById(@PathVariable Long id) {
      return entregaPedidoService.obterEntregaPorId(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public EntregaPedido createPedido(@RequestBody EntregaPedido entrega) {
      return entregaPedidoService.criarEntrega(entrega);
   }

   @PutMapping("/{entregaId}/assign/{entregadorId}")
   public EntregaPedido assignEntregador(@PathVariable Long entregaId, @PathVariable Long entregadorId) {
      return entregaPedidoService.assignEntregador(entregaId, entregadorId);
   }

   @GetMapping("/{entregaId}/calcular-rota")
   public ResponseEntity<DirectionsResult> calcularRota(@PathVariable Long entregaId) {
      try {
         DirectionsResult result = entregaPedidoService.calcularRota(entregaId);
         return ResponseEntity.ok(result);
      } catch (Exception e) {
         return ResponseEntity.status(500).build();
      }
   }

   @GetMapping("/{entregaId}/estimar-tempo-entrega")
   public ResponseEntity<Long> estimarTempoEntrega(@PathVariable Long entregaId) {
      try {
         long tempo = entregaPedidoService.estimarTempoEntrega(entregaId);
         return ResponseEntity.ok(tempo);
      } catch (Exception e) {
         return ResponseEntity.status(500).build();
      }
   }

   @GetMapping("/{entregaId}/rastrear")
   public ResponseEntity<String> rastrearEntrega(@PathVariable Long entregaId) {
      try {
         String coordenadas = entregaPedidoService.rastrearEntrega(entregaId);
         return ResponseEntity.ok(coordenadas);
      } catch (Exception e) {
         return ResponseEntity.status(500).build();
      }
   }
}
