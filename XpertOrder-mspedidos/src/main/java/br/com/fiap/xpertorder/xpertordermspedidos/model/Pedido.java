package br.com.fiap.xpertorder.xpertordermspedidos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("pedido")
public class Pedido {

   @Id
   private String id;
   private List<PedidoCliente> clientes;
   private List<ItemPedido> itensPedido;
   private double valorTotal;
}
