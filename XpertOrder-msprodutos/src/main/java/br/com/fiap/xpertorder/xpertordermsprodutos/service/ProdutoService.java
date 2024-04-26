package br.com.fiap.xpertorder.xpertordermsprodutos.service;

import br.com.fiap.xpertorder.xpertordermsprodutos.model.Produto;
import br.com.fiap.xpertorder.xpertordermsprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

   @Autowired
   private ProdutoRepository produtoRepository;

   public Produto criarProduto(Produto produto){
      return produtoRepository.save(produto);
   }

   public List<Produto> listarProdutos(){
      return produtoRepository.findAll();
   }

   public ResponseEntity<?> listarUmProduto(Integer produtoId) {
      Produto produto = produtoRepository
              .findById(produtoId)
              .orElse(null);

      if (produto != null) {
         return ResponseEntity.ok(produto);
      } else {
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body("Produto não encontrado!");
      }
   }

   public Produto atualizarProduto(Integer produtoId, Produto produtoNovo) {
      Produto produto = produtoRepository
              .findById(produtoId)
              .orElse(null);

      if (produto != null) {
         produto.setNome(produtoNovo.getNome());
         produto.setDescricao(produtoNovo.getDescricao());
         produto.setQuantidade_estoque(produtoNovo.getQuantidade_estoque());
         produto.setPreco(produtoNovo.getPreco());

         return produtoRepository.save(produto);

      } else {
         throw new NoSuchElementException("Produto não encontrado!");
      }
   }

   public void excluirProduto(Integer produtoId){
      Produto produto = produtoRepository
              .findById(produtoId)
              .orElse(null);

      if (produto != null) {
         produtoRepository.delete(produto);

      } else {
         throw new NoSuchElementException("Produto não encontrado!");
      }
   }

   public Produto atualizarEstoque(Integer produtoId, int quantidade) {
      Produto produto = produtoRepository.findById(produtoId).orElse(null);

      if (produto != null) {
         produto.setQuantidade_estoque(produto.getQuantidade_estoque() - quantidade);

         return produtoRepository.save(produto);
      }

      return null;
   }

}
