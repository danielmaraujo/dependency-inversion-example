package br.edu.iff.repository;

import br.edu.iff.entity.Produto;

import java.util.HashMap;
import java.util.Map;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private static final Map<String, Produto> cache = new HashMap<>();

    @Override
    public Produto buscar(String codigo) {
        return cache.get(codigo);
    }

    @Override
    public void inserir(Produto produto) {
        if (produto.getCodigo() != null) {
            cache.put(produto.getCodigo(), produto);
        }else{
            throw new IllegalArgumentException("Produto sem código");
        }
    }
}
