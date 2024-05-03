package br.com.fiap.xpertorder.xpertordermspedidos.service;

import br.com.fiap.xpertorder.xpertordermspedidos.model.ItemPedido;
import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import br.com.fiap.xpertorder.xpertordermspedidos.model.PedidoCliente;
import br.com.fiap.xpertorder.xpertordermspedidos.repository.PedidoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PedidoService {

   @Autowired
   private PedidoRepository pedidoRepository;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private ObjectMapper objectMapper;

   public Pedido criarPedido(Pedido pedido) {
      boolean produtosDisponiveis = verificarDisponibilidadeProdutos(pedido.getItensPedido());

      if (!produtosDisponiveis) {
         throw new NoSuchElementException("Um ou mais produtos não estão disponíveis.");
      }

      double valorTotal = calcularValorTotal(pedido.getItensPedido());
      pedido.setValorTotal(valorTotal);

      atualizarEstoque(pedido.getItensPedido());

      return pedidoRepository.save(pedido);
   }

   private void vincularClienteAoPedido(List<PedidoCliente> pedidoClientes) {
      for (PedidoCliente pedidoCliente : pedidoClientes) {
         UUID idCliente = pedidoCliente.getIdCliente();
         String nomeCliente = pedidoCliente.getNomeCliente();
         String cpf = pedidoCliente.getCpf();

         restTemplate.put(
                 "http://localhost:8082/api/clientes/{clienteId}",
                 null,
                 idCliente,
                 nomeCliente,
                 cpf
         );

      }
   }

   private boolean verificarDisponibilidadeProdutos(List<ItemPedido> itensPedido) {
      for (ItemPedido itemPedido : itensPedido) {
         Integer idProduto = itemPedido.getIdProduto();
         int quantidade = itemPedido.getQuantidade();

         ResponseEntity<String> resp = restTemplate.getForEntity(
                 "http://localhost:8080/api/produtos/{produtoId}",
                 String.class,
                 idProduto
         );

         if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NoSuchElementException("Produto não encontrado");
         } else {
            try {
               JsonNode produtoJson = objectMapper.readTree((resp.getBody()));
               int quantidadeEstoque = produtoJson.get("quantidade_estoque").asInt();

               if (quantidadeEstoque < quantidade) {
                  return false;
               }
            } catch (IOException e) {
               throw new RuntimeException("Estoque insuficiente", e);
            }
         }
      }

      return true;
   }

   private double calcularValorTotal(List<ItemPedido> itensPedido) {
      double valorTotal = 0.0;

      for (ItemPedido itemPedido : itensPedido) {
         Integer idProduto = itemPedido.getIdProduto();
         int quantidade = itemPedido.getQuantidade();

         ResponseEntity<String> response = restTemplate.getForEntity(
                 "http://localhost:8080/api/produtos/{produtoId}",
                 String.class,
                 idProduto
         );

         if (response.getStatusCode() == HttpStatus.OK) {
            try {
               JsonNode produtoJson = objectMapper.readTree(response.getBody());
               double preco = produtoJson.get("preco").asDouble();
               valorTotal = preco * quantidade;
            } catch (IOException e) {
               // tratar dps
            }
         }
      }
      return valorTotal;
   }

   private void atualizarEstoque(List<ItemPedido> itensPedido) {
      for (ItemPedido itemPedido : itensPedido) {
         Integer idProduto = itemPedido.getIdProduto();
         int quantidade = itemPedido.getQuantidade();

         restTemplate.put(
                 "http://localhost:8080/api/produtos/atualizar/estoque/{produtoId}/{quantidade}",
                 null,
                 idProduto,
                 quantidade
         );

      }
   }

   public List<Pedido> listarPedidos() {
      return pedidoRepository.findAll();
   }

   public ResponseEntity<?> listarUmPedido(@PathVariable String pedidoId) {
      Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

      if (pedido != null) {
         return ResponseEntity.ok(pedido);
      } else {
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body("Pedido não encontrado.");
      }
   }

   public Pedido atualizarPedido(String pedidoId, Pedido pedidoNovo) {
      Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

      if (pedido != null) {
         pedido.setClientes(pedidoNovo.getClientes());
         pedido.setItensPedido(pedidoNovo.getItensPedido());

         return pedidoRepository.save(pedido);
      } else {
         throw new NoSuchElementException("Pedido não encontrado.");
      }
   }

   public void excluirPedido(String pedidoId) {
      Pedido pedido = pedidoRepository
              .findById(pedidoId)
              .orElse(null);

      if (pedido != null) {
         pedidoRepository.delete(pedido);
      } else {
         throw new NoSuchElementException("Pedido não encontrado");
      }
   }
}
