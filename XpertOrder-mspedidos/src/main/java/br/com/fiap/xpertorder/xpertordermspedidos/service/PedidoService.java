package br.com.fiap.xpertorder.xpertordermspedidos.service;

import br.com.fiap.xpertorder.xpertordermspedidos.model.ItemPedido;
import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import br.com.fiap.xpertorder.xpertordermspedidos.repository.PedidoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

      return pedidoRepository.save(pedido);
   }
   
   private boolean verificarDisponibilidadeProdutos(List<ItemPedido> itensPedido){
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
               // tratar dps
            }
         }
      }

      return true;
   }
}
