package br.com.fiap.xpertorder.xpertordermspedidos.controller;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import br.com.fiap.xpertorder.xpertordermspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

   @Autowired
   private PedidoService pedidoService;

   @PostMapping
   public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido){

      try {
         Pedido pedidoNovo = pedidoService.criarPedido(pedido);

         return new ResponseEntity<>(pedidoNovo, HttpStatus.CREATED);

      } catch (NoSuchElementException e) {
         return new ResponseEntity<>("Um ou mais produtos estão indisponíveis.", HttpStatus.BAD_REQUEST);
      }
   }

   @GetMapping
   public List<Pedido> listarPedidos() {
      return pedidoService.listarPedidos();
   }

   @GetMapping("/{pedidoId}")
   public ResponseEntity<?> listarUmPedido(@PathVariable String pedidoId){
      return pedidoService.listarUmPedido(pedidoId);
   }

   @PutMapping("/{pedidoId}")
   public Pedido atualizarPedido(@PathVariable String pedidoId,
                                 @RequestBody Pedido pedidoNovo){
      return pedidoService.atualizarPedido(pedidoId, pedidoNovo);
   }

   @DeleteMapping("/{pedidoId}")
   public void excluirPedido(@PathVariable String pedidoId) {
      pedidoService.excluirPedido(pedidoId);
   }
}
