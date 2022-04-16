package estudos.ecommerce.categoria.adapter.in.web.request;

import estudos.ecommerce.categoria.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CategoriaRequest {

    @NotBlank(message = "Campo nome não pode estar em branco")
    private String nome;

    public Categoria toModel(CategoriaRequest request){
        return new Categoria(request.getNome());
    }
}
