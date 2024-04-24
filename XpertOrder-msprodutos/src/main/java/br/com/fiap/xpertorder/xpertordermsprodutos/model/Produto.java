package br.com.fiap.xpertorder.xpertordermsprodutos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Produto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String descricao;
   private int quantidade_estoque;
   private double preco;
   private LocalDateTime data_criacao;

}
