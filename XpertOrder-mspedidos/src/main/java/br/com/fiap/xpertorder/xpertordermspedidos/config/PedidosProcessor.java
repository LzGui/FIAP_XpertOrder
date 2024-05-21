package br.com.fiap.xpertorder.xpertordermspedidos.config;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;

public class PedidosProcessor implements ItemProcessor<Pedido, Pedido> {

   @Override
   public Pedido process(Pedido pedido) throws Exception {
      pedido.setCreateDateTime(LocalDateTime.now());
      return pedido;
   }
}
