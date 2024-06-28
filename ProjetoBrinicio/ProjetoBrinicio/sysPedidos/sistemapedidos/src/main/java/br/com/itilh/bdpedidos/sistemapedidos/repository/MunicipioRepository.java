package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio , BigInteger > {

    List<Municipio> findByEstadoNomeIgnoreCase(String nome);

    List<Municipio> findByEstadoId(BigInteger id);
 
    @Query("FROM Municipio e WHERE e.nome like %?1")
    List<Municipio> findByMinhaQuery(String nome);
}
