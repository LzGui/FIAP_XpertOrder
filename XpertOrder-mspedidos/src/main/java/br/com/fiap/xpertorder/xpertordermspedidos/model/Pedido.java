package br.com.fiap.xpertorder.xpertordermspedidos.model;

import java.util.List;

public class Pedido {

   private String id;
   private String nomeCliente;
   private List<ItemProduto> itensPedido;
   private double valorTotal;
}
