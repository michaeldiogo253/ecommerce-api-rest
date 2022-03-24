package estudos.ecommerce.produto.application.service;

import estudos.ecommerce.categoria.application.port.out.FindCategoriaByNomePort;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.produto.application.port.in.CadastrarProdutoUseCase;
import estudos.ecommerce.produto.application.port.out.SaveProdutoPort;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CadastraProdutoService implements CadastrarProdutoUseCase {

    private final FindCategoriaByNomePort findCategoriaByNomePort;
    private final SaveProdutoPort saveProdutoPort;

    @Override
    public Produto execute(Produto produto, String nomeCategoria) {

        Categoria categoriaBuscada = findCategoriaByNomePort.findCategoriaPorNome(nomeCategoria);
        produto.setCategoria(categoriaBuscada);
        return saveProdutoPort.salvarProduto(produto);

    }
}
