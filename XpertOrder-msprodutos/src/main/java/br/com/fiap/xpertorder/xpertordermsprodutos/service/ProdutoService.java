package br.com.fiap.xpertorder.xpertordermsprodutos.service;

import br.com.fiap.xpertorder.xpertordermsprodutos.model.Produto;
import br.com.fiap.xpertorder.xpertordermsprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

   @Autowired
   private ProdutoRepository produtoRepository;

   public List<Produto> listarProdutos(){
      return produtoRepository.findAll();
   }

}
