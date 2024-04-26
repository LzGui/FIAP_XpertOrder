package br.com.fiap.xpertorder.xpertordermspedidos.controller;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import br.com.fiap.xpertorder.xpertordermspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
