package estudos.ecommerce.itemdocarrinho.application.port.in;

public interface RemoveItemDoCarrinhoUseCase {

    void execute(Long idCliente, Long idProduto);

}
