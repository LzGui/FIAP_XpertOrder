package br.com.fiap.xpertorder.xpertordermslogistica.service;

import br.com.fiap.xpertorder.xpertordermslogistica.model.EntregaPedido;
import br.com.fiap.xpertorder.xpertordermslogistica.model.EntregadorPedido;
import br.com.fiap.xpertorder.xpertordermslogistica.model.StatusPedido;
import br.com.fiap.xpertorder.xpertordermslogistica.repository.EntregaPedidoRepository;
import br.com.fiap.xpertorder.xpertordermslogistica.repository.EntregadorPedidoRepository;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntregaPedidoService {
   @Autowired
   private EntregaPedidoRepository entregaPedidoRepository;

   @Autowired
   private EntregadorPedidoRepository entregadorPedidoRepository;

   @Autowired
   private RotaService rotaService;

   public List<EntregaPedido> obterTodasEntregas(){
      return entregaPedidoRepository.findAll();
   }

   public Optional<EntregaPedido> obterEntregaPorId(Long id) {
      return entregaPedidoRepository.findById(id);
   }

   public EntregaPedido criarEntrega(EntregaPedido entrega){
      entrega.setDataCriacao(LocalDateTime.now());
      entrega.setStatus(StatusPedido.PENDENTE);
      return entregaPedidoRepository.save(entrega);
   }

   public EntregaPedido assignEntregador(Long entregaId, Long entregadorId) {
      EntregaPedido entrega = entregaPedidoRepository
              .findById(entregaId)
              .orElseThrow(() -> new RuntimeException("Entrega não encontrado"));

      EntregadorPedido entregador = entregadorPedidoRepository
              .findById(entregadorId)
              .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

      entrega.setEntregador(entregador);
      entrega.setStatus(StatusPedido.EM_ROTA);

      return entregaPedidoRepository.save(entrega);
   }

   public DirectionsResult calcularRota(Long entregaId) throws InterruptedException, ApiException, IOException {
      EntregaPedido entrega = entregaPedidoRepository.findById(entregaId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
      String origem = entrega.getEntregador().getCoordenadasAtuais();
      String destino = entrega.getCoordenadasEntrega();
      return rotaService.calcularRota(origem, destino);
   }

   public long estimarTempoEntrega(Long entregaId) throws InterruptedException, ApiException, IOException {
      EntregaPedido entrega = entregaPedidoRepository.findById(entregaId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
      String origem = entrega.getEntregador().getCoordenadasAtuais();
      String destino = entrega.getCoordenadasEntrega();
      return rotaService.estimarTempoEntrega(origem, destino);
   }

   public String rastrearEntrega(Long entregaId) {
      EntregaPedido entrega = entregaPedidoRepository.findById(entregaId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
      return entrega.getEntregador().getCoordenadasAtuais();
   }
}
