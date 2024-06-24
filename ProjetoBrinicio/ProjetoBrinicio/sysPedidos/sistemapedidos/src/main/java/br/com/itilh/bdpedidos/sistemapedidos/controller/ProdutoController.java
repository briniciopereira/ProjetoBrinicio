package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProdutoController {
   private final ProdutoRepository repositorio;

   public ProdutoController (ProdutoRepository repositorio){
               this.repositorio=repositorio;

   }

@GetMapping("/produto")
public List<Produto> getProduto() {
    return (List<Produto>) repositorio.findAll();
}
 @GetMapping("/produto/{id}")
    public Produto getPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
            () -> new Exception("ID inválido.")
         );
    }    


    @PostMapping("/produto")
    public Produto criarProduto(@RequestBody Produto entity) throws Exception { 
        try{               
            return repositorio.save(entity);
        }catch(Exception e){
            throw new Exception("Erro ao salvar o produto.");
        }
    }

    @PutMapping("/produto/{id}")
    public Produto alterarEstado(@PathVariable BigInteger id, 
                                @RequestBody Produto novosDados) throws Exception {

        Optional<Produto> produtoAmazenado = repositorio.findById(id);
        if(produtoAmazenado.isPresent()){
           
            produtoAmazenado.get().setNome(novosDados.getNome());
            
            return repositorio.save(produtoAmazenado.get());
        }        
        throw new Exception("Alteração não foi realizada.");
    }

    @DeleteMapping("/produto/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Produto> produtoAmazenado = repositorio.findById(id);
        if(produtoAmazenado.isPresent()){
            repositorio.delete(produtoAmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não econtrado para a exclusão");
    }    

}
