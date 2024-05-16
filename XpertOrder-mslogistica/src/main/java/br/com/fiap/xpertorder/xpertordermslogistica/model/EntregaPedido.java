package br.com.fiap.xpertorder.xpertordermslogistica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EntregaPedido {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idEntrega;

   private String descricao;

   @Enumerated(EnumType.STRING)
   private StatusPedido status;

   private LocalDateTime dataCriacao;

   private String enderecoEntrega;

   private String coordenadasEntrega;

   @ManyToOne
   @JoinColumn(name = "entregador_id")
   private EntregadorPedido entregador;

}
