package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteRepository repositorio;
    
    public  ClienteController (ClienteRepository repositorio){
      this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Cliente> getCliente() {
        return (List<Cliente>) repositorio.findAll();
    }
    
     @GetMapping("/clientes/{id}")
    public Cliente getPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
            () -> new Exception("ID inválido.")
         );
    }    


    @PostMapping("/clientes")
    public Cliente criarCliente(@RequestBody Cliente entity) throws Exception { 
        try{               
            return repositorio.save(entity);
        }catch(Exception e){
            throw new Exception("Erro ao salvar o cliente.");
        }
    }

    @PutMapping("/clientes/{id}")
    public Cliente alterarCliente(@PathVariable BigInteger id, 
                                @RequestBody Cliente novosDados) throws Exception {

        Optional<Cliente> clienteAmazenado = repositorio.findById(id);
        if(clienteAmazenado.isPresent()){
            clienteAmazenado.get().setNome(novosDados.getNome());
            
            return repositorio.save(clienteAmazenado.get());
        }        
        throw new Exception("Alteração não foi realizada.");
    }

    @DeleteMapping("/cliente/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Cliente> clienteAmazenado = repositorio.findById(id);
        if(clienteAmazenado.isPresent()){
            repositorio.delete(clienteAmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não econtrado para a exclusão");
    }    

}
