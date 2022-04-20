package estudos.ecommerce.categoria.application.port.in;

public interface AlterarCategoriaUseCase {

    void execute(Long idCategoria, String novoNomeCategoria);
}
