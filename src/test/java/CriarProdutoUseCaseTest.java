import br.edu.iff.entity.Produto;
import br.edu.iff.repository.ProdutoRepository;
import br.edu.iff.usecase.CriarProdutoUseCase;
import org.junit.jupiter.api.Test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriarProdutoUseCaseTest {
    @Test
    public void testExecutar() {
        // Cria um mock da interface ProdutoRepository usando o Mockito
        ProdutoRepository mockRepository = mock(ProdutoRepository.class);

        // Cria uma instância de CriarProdutoUseCase, passando o mockRepository como dependência
        CriarProdutoUseCase useCase = new CriarProdutoUseCase(mockRepository);

        // Define os valores de teste para o novo produto
        String nomeProduto = "Novo Produto";
        double precoProduto = 99.99;

        // Define o comportamento esperado do mock para o método buscar()
        when(mockRepository.buscar(anyString())).thenReturn(null);

        // Chama o método executar()
        String codigoRetornado = useCase.executar(nomeProduto, precoProduto);

        //Verifica se o método inserir() foi chamado
        verify(mockRepository, times(1)).inserir(any(Produto.class));

        //Verifica se o método inserir() foi chamado
        verify(mockRepository, times(1)).buscar(anyString());
    }

    @Test
    public void testExecutar_PrimeiroCodigoGeradoJaExiste() {
        ProdutoRepository mockRepository = mock(ProdutoRepository.class);

        CriarProdutoUseCase useCase = new CriarProdutoUseCase(mockRepository);

        String nomeProduto = "Novo Produto";
        double precoProduto = 99.99;

        //Mock retorna um produto na primeira chamada
        when(mockRepository.buscar(anyString()))
            .thenReturn(new Produto("123", "Produto existente", 99.99))
            .thenReturn(null);

        String codigoRetornado = useCase.executar(nomeProduto, precoProduto);

        verify(mockRepository, times(1)).inserir(any(Produto.class));

        //Verifica se o método inserir() foi chamado duas vezes
        verify(mockRepository, times(2)).buscar(anyString());
    }
}
