package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto,BigInteger>{

}
