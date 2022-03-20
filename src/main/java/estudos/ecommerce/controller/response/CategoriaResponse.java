package estudos.ecommerce.controller.response;

import estudos.ecommerce.produto.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class CategoriaResponse {
    private Long id;
    private String nome;

    public static List<CategoriaResponse> from(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaResponse::from).collect(Collectors.toList());
    }

    public static CategoriaResponse from(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }

}
