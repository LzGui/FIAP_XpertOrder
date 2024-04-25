package br.com.fiap.xpertorder.xpertordermsprodutos.controller;

import br.com.fiap.xpertorder.xpertordermsprodutos.model.Produto;
import br.com.fiap.xpertorder.xpertordermsprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

   @Autowired
   private ProdutoService produtoService;

   @PostMapping
   public Produto criarProduto(@RequestBody Produto produto){
      return produtoService.criarProduto(produto);
   }

   @GetMapping
   public List<Produto> listarProdutos(){
      return produtoService.listarProdutos();
   }

   @GetMapping("/{produtoId}")
   public ResponseEntity<?> listarUmProduto(@PathVariable Integer produtoId){
      return produtoService.listarUmProduto(produtoId);
   }

   @PutMapping("/{produtoId}")
   public Produto atualizarProduto(@PathVariable Integer produtoId,
                                   @RequestBody Produto produtoNovo){
      return produtoService.atualizarProduto(produtoId, produtoNovo);
   }

   @DeleteMapping("/{produtoId}")
   public void excluirProduto(@PathVariable Integer produtoId) {
      produtoService.excluirProduto(produtoId);
   }
}
