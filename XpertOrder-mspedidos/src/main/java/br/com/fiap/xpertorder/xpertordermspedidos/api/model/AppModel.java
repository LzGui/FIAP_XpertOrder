package br.com.fiap.xpertorder.xpertordermspedidos.api.model;

import br.com.fiap.xpertorder.xpertordermspedidos.model.ItemPedido;

import java.util.List;
import java.util.UUID;

public class AppModel {

   private String id;
   private UUID idCliente;
   private List<ItemPedido> itensPedido;
   private double valorTotal;
}
