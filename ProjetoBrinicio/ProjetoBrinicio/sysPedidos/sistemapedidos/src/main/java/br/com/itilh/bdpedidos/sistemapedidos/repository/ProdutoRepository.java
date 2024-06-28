package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto,BigInteger>{
  List<Produto> findByNomeEndingWithIgnoreCase(String nome);

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    List<Produto> findByNomeStartingWithIgnoreCase(String nome);

    List<Produto> findByNome(String nome);

     @Query("FROM Produto e WHERE e.nome like %?1")
    List<Produto> findByMinhaQuery(String nome);
}
