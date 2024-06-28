package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;



@Repository
public interface ClienteRepository extends CrudRepository<Cliente, BigInteger>{
   List<Cliente> findByNomeEndingWithIgnoreCase(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

    List<Cliente> findByNome(String nome);

     @Query("FROM Cliente e WHERE e.nome like %?1")
    List<Cliente> findByMinhaQuery(String nome);
}
