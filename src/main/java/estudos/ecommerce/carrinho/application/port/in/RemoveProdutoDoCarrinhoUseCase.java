package estudos.ecommerce.carrinho.application.port.in;

public interface RemoveProdutoDoCarrinhoUseCase {

    void execute(Long idCliente, Long idProduto);
}
