package estudos.ecommerce.carrinho.application.port.in;

public interface RemoveProdutoDoCarrinhoPorQuantidadeUseCase {

    void execute(Long idCliente, Long idProduto, Integer quantidade );
}
