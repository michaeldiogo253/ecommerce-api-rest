package estudos.ecommerce.produto.adapter.out.persistence;

import estudos.ecommerce.produto.application.port.out.DeleteProdutoByIdPort;
import estudos.ecommerce.produto.application.port.out.SaveProdutoPort;
import estudos.ecommerce.produto.domain.Produto;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoPersistenceAdapter implements SaveProdutoPort, DeleteProdutoByIdPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto salvarProduto(Produto produto) {

        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProdutoPorId(Long idProduto) {

        if(produtoRepository.existsById(idProduto)){
            produtoRepository.deleteById(idProduto);
            return;
        }
        throw new ResourceNotFoundException("Produto não encontrado");
    }
}
