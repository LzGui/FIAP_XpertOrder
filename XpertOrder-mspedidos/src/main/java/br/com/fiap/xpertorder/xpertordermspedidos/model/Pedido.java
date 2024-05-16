package br.com.fiap.xpertorder.xpertordermspedidos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document("pedido")
public class Pedido {

   @Id
   private String id;
   private UUID idCliente;
   private List<ItemPedido> itensPedido;
   private double valorTotal;
}
