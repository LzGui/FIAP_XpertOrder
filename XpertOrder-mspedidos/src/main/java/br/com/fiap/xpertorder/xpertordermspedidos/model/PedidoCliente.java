package br.com.fiap.xpertorder.xpertordermspedidos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoCliente {

   private UUID idCliente;
   private String nomeCliente;
   private String cpf;
}
