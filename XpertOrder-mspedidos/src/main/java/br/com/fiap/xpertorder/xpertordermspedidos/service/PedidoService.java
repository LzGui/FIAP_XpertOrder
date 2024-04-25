package br.com.fiap.xpertorder.xpertordermspedidos.service;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import br.com.fiap.xpertorder.xpertordermspedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

   @Autowired
   private PedidoRepository pedidoRepository;

   public Pedido criarPedido(Pedido pedido) {
      return pedidoRepository.save(pedido);
   }
}
