package com.example.exerciciossb.controllers;

import com.example.exerciciossb.model.entidades.Produto;
import com.example.exerciciossb.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public @ResponseBody Produto novoProduto(@Valid Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping(path = "/pagina/{numPagina}/{qtdPagina}")
    public Iterable<Produto> obterProdutosPorPagina(@PathVariable int numPagina,@PathVariable int qtdPagina){
        Pageable page = PageRequest.of(numPagina,qtdPagina);
        return produtoRepository.findAll(page);
    }

    @GetMapping
    public Iterable<Produto> retornaProdutos(){
        return produtoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProduto(@PathVariable int id){
        return produtoRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void excluirProduto(@PathVariable int id){
        produtoRepository.deleteById(id);
    }

    @GetMapping(path = "/nome/{nome}")
    public Iterable<Produto> produtoFindByName(@PathVariable String nome){
        return produtoRepository.findByNomeContaining(nome);
    }
}
