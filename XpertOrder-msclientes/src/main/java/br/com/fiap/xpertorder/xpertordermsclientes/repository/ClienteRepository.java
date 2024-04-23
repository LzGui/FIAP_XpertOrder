package br.com.fiap.xpertorder.xpertordermsclientes.repository;

import br.com.fiap.xpertorder.xpertordermsclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
