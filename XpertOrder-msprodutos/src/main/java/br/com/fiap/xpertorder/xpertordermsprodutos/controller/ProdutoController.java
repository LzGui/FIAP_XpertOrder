package br.com.fiap.xpertorder.xpertordermsprodutos.controller;

import br.com.fiap.xpertorder.xpertordermsprodutos.model.Produto;
import br.com.fiap.xpertorder.xpertordermsprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

   @Autowired
   private ProdutoService produtoService;

   @GetMapping
   public List<Produto> listarProdutos(){
      return produtoService.listarProdutos();
   }
}
