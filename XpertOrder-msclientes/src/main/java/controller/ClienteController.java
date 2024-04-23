package controller;

import model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClienteService;

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

   @GetMapping("/{id}")
   public ResponseEntity<?> obterCliente(@PathVariable UUID id){
      return clienteService.listarCliente(id);
   }

   @PutMapping("/{id}")
   public Cliente atualizarCliente(@PathVariable UUID id,
                                   @RequestBody Cliente cliente) {

      return clienteService.atualizarCliente(id, cliente);
   }

   @DeleteMapping("/{id}")
   public void deletarCliente(@PathVariable UUID id) {
      clienteService.deletarCliente(id);
   }
}
