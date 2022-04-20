package estudos.ecommerce.categoria.application.port.out;

public interface DeletarCategoriaByIdPort {

    Class<? extends Throwable> deletarCategoriaPorId(Long idCategoria);
}
