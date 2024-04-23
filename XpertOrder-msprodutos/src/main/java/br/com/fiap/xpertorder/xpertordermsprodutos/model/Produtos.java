package br.com.fiap.xpertorder.xpertordermsprodutos.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class Produtos {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private UUID id;
   private String nomeProduto;
   private String descricaoProduto;
   private Estoque estoque;

}
