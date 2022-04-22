package estudos.ecommerce.databuilders;

import estudos.ecommerce.categoria.domain.Categoria;

public class CategoriaCreator {

    public static Categoria umaCategoria(String nome) {

        return new Categoria(nome);
    }
}
