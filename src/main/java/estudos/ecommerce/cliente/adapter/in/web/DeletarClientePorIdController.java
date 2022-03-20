package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.application.port.in.DeletarClientePorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/cliente")
public class DeletarClientePorIdController {

    private final DeletarClientePorIdUseCase deletarClientePorIdUseCase;

    @DeleteMapping("/deletar/{idCliente}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable Long idCliente) {

        deletarClientePorIdUseCase.execute(idCliente);
        return ResponseEntity.ok()
                             .build();
    }
}
