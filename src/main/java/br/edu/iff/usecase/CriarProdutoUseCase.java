package br.edu.iff.usecase;

import br.edu.iff.entity.Produto;
import br.edu.iff.repository.ProdutoRepository;

import java.util.UUID;

public class CriarProdutoUseCase {
    private final ProdutoRepository produtoRepository;

    public CriarProdutoUseCase(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public String executar(String nome, double preco) {
        String codigo;

        do {
            codigo = UUID.randomUUID().toString();
        } while (produtoRepository.buscar(codigo) != null);

        produtoRepository.inserir(new Produto(codigo, nome, preco));

        return codigo;
    }
}
