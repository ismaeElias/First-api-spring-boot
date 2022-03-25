package com.example.exerciciossb.model.repositories;

import com.example.exerciciossb.model.entidades.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

    public Iterable<Produto> findByNomeContaining(String parteNome);
}
