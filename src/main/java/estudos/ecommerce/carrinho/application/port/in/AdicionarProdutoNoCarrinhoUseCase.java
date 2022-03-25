package estudos.ecommerce.carrinho.application.port.in;

public interface AdicionarProdutoNoCarrinhoUseCase {

    void execute(Long idCliente,Long idProduto,Integer quantidade);

}
