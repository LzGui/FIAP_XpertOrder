package br.com.fiap.xpertorder.xpertordermspedidos.repository;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido,String> {
}
