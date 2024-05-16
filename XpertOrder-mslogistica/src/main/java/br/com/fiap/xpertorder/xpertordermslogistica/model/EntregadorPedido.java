package br.com.fiap.xpertorder.xpertordermslogistica.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class EntregadorPedido {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   private String veiculo;

   private String coordenadasAtuais;

   @OneToMany(mappedBy = "entregador")
   private List<EntregaPedido> entregas;
}
