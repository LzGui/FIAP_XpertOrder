package br.com.fiap.xpertorder.xpertordermspedidos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemProduto {
   private Integer idProduto;
   private int quantidade;
}
