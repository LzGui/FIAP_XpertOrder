package br.com.fiap.xpertorder.xpertordermsclientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private UUID id;
   private String nomeCliente;
   private String enderecoCliente;
   private String cpf;
   private String dataNascimento;


}
