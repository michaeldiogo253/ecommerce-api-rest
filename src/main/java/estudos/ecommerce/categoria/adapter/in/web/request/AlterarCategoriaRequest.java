package estudos.ecommerce.categoria.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AlterarCategoriaRequest {

    @NotNull(message = "Campo ID não pode ser nulo") private Long idCategoria;
    @NotBlank(message = "Campo nome não pode estar em branco") private String nome;

}
