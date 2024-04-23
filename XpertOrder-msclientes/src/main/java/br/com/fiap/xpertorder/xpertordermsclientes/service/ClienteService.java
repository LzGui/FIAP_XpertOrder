package br.com.fiap.xpertorder.xpertordermsclientes.service;

import br.com.fiap.xpertorder.xpertordermsclientes.repository.ClienteRepository;
import br.com.fiap.xpertorder.xpertordermsclientes.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository clienteRepository;

   public Cliente criarCliente(Cliente cliente) {
      return clienteRepository.save(cliente);
   }

   public List<Cliente> listarClientes(){
      return clienteRepository.findAll();
   }

   public ResponseEntity<?> listarCliente(UUID id){
      Cliente cliente = clienteRepository.findById(id).orElse(null);

      if (cliente != null) {
         return ResponseEntity.ok(cliente);
      } else {
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body("Cliente não encontrado!");
      }
   }

   public Cliente atualizarCliente(UUID id, Cliente clienteAtualizado) {
      Cliente cliente = clienteRepository.findById(id).orElse(null);

      if (cliente != null) {
         cliente.setNomeCliente(clienteAtualizado.getNomeCliente());
         cliente.setEnderecoCliente(clienteAtualizado.getEnderecoCliente());
         cliente.setCpf(clienteAtualizado.getCpf());
         cliente.setDataNascimento(clienteAtualizado.getDataNascimento());

         return clienteRepository.save(cliente);

      } else {
         throw new NoSuchElementException("Cliente não encontrado!");
      }
   }

   public void deletarCliente(UUID id) {
      Cliente cliente = clienteRepository.findById(id).orElse(null);

      if (cliente != null) {
         clienteRepository.delete(cliente);
      } else {
         throw new NoSuchElementException("Cliente não encontrado!");
      }
   }
}
