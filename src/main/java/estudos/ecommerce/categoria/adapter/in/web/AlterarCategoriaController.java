package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.request.AlterarCategoriaRequest;
import estudos.ecommerce.categoria.application.port.in.AlterarCategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlterarCategoriaController {

    private final AlterarCategoriaUseCase alterarCategoriaUseCase;

    @PutMapping("")
    public ResponseEntity<Void> alterarCategoria(@RequestBody @Valid AlterarCategoriaRequest request){

        alterarCategoriaUseCase.execute(request.getIdCategoria(), request.getNome());

        return ResponseEntity.ok().build();
    }

}
