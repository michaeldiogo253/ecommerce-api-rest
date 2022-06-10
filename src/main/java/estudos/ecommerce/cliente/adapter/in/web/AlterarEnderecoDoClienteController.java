package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.request.AtualizaEnderecoClienteRequest;
import estudos.ecommerce.cliente.application.service.AtualizaEnderecoClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce-api/cliente")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlterarEnderecoDoClienteController {

    private final AtualizaEnderecoClienteService atualizaEnderecoClienteService;

    @PutMapping(path = "/atualiza-endereco/{idCliente}")
    public ResponseEntity<Void> execute(@PathVariable(value = "idCliente") Long idCliente,
                                        @RequestBody AtualizaEnderecoClienteRequest request) {

        atualizaEnderecoClienteService.atualizaEndereco(request.toModel(), idCliente);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}