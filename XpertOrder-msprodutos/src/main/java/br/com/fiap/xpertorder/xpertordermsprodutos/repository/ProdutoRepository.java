package br.com.fiap.xpertorder.xpertordermsprodutos.repository;

import br.com.fiap.xpertorder.xpertordermsprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
