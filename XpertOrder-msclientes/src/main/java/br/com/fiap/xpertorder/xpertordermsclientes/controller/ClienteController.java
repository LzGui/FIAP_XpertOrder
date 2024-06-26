package br.com.fiap.xpertorder.xpertordermsclientes.controller;

import br.com.fiap.xpertorder.xpertordermsclientes.service.ClienteService;
import br.com.fiap.xpertorder.xpertordermsclientes.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

   @Autowired
   private ClienteService clienteService;

   @PostMapping
   public Cliente criarCliente(@RequestBody Cliente cliente) {
      return clienteService.criarCliente(cliente);
   }

   @GetMapping
   public List<Cliente> obterClientes() {
      return clienteService.listarClientes();
   }

   @GetMapping("/{clienteId}")
   public ResponseEntity<?> obterCliente(@PathVariable UUID id){
      return clienteService.listarCliente(id);
   }

   @PutMapping("/{clienteId}")
   public Cliente atualizarCliente(@PathVariable UUID id,
                                   @RequestBody Cliente cliente) {

      return clienteService.atualizarCliente(id, cliente);
   }

   @DeleteMapping("/{clienteId}")
   public void deletarCliente(@PathVariable UUID id) {
      clienteService.deletarCliente(id);
   }
}
