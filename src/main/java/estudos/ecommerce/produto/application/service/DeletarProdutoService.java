package estudos.ecommerce.produto.application.service;

import estudos.ecommerce.produto.application.port.in.DeletarProdutoPorIdUseCase;
import estudos.ecommerce.produto.application.port.out.DeleteProdutoByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletarProdutoService implements DeletarProdutoPorIdUseCase {

    private final DeleteProdutoByIdPort deleteProdutoByIdPort;

    @Override
    public void execute(Long idProduto) {
        deleteProdutoByIdPort.deletarProdutoPorId(idProduto);
    }
}
