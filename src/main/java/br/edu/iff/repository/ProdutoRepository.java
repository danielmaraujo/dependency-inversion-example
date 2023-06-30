package br.edu.iff.repository;

import br.edu.iff.entity.Produto;

public interface ProdutoRepository {

    Produto buscar(String codigo);
    void inserir(Produto produto);
}
