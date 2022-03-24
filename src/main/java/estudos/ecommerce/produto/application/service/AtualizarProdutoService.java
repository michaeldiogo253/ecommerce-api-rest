package estudos.ecommerce.produto.application.service;

import estudos.ecommerce.produto.application.port.in.AtualizarProdutoUseCase;
import estudos.ecommerce.produto.application.port.out.FindProdutoByIdPort;
import estudos.ecommerce.produto.application.port.out.SaveProdutoPort;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtualizarProdutoService implements AtualizarProdutoUseCase {
    private final FindProdutoByIdPort findProdutoByIdPort;
    private final SaveProdutoPort saveProdutoPort;

    @Override
    public Produto execute(Long idProduto, String nome, String descricao, BigDecimal preco) {

        Produto produto = findProdutoByIdPort.findProdutoById(idProduto);
        produto.atualizaDadosDoProduto(nome, descricao, preco);
        saveProdutoPort.salvarProduto(produto);

        return produto;
    }
}
