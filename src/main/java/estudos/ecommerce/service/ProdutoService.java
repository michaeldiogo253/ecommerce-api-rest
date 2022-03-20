package estudos.ecommerce.service;

import estudos.ecommerce.controller.request.ProdutoRequest;
import estudos.ecommerce.produto.domain.Categoria;
import estudos.ecommerce.produto.domain.Produto;
import estudos.ecommerce.repository.CategoriaRepository;
import estudos.ecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public void cadastrarProduto(ProdutoRequest request) {

        Categoria categoria = buscaCategoriasPorNome(request.getCategoria());
        Produto produto = Produto.from(request, categoria);
        produtoRepository.save(produto);
    }

    private Categoria buscaCategoriasPorNome(String categoria) {

        Optional<Categoria> categoriaBuscada = categoriaRepository.findCategoriaByNome(categoria);

        if (categoriaBuscada.isEmpty()) {
            throw new NoSuchElementException("Não foi encontrado nenhuma categoria valida...");
        }

        return categoriaBuscada.get();
    }

    public Produto buscaProdutoPorId(Long idProduto) {
        return produtoRepository.findById(idProduto).orElseThrow(()-> new IllegalArgumentException("Produto não encontrado"));
    }
}
