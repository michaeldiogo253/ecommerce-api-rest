package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.cliente.application.port.out.FindOptionalClienteByIdPort;
import estudos.ecommerce.util.config.ModelMapperConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecommerce-api/cliente")
public class FindClienteByIdSemLancarExceptionController {

    private final FindOptionalClienteByIdPort findOptionalClienteByIdPort;
    private final ModelMapper modelMapper;

    @GetMapping("/busca-cliente/{idCliente}")
    public ResponseEntity<ClienteResponse> buscaClientePorId(@PathVariable Long idCliente) {

        return findOptionalClienteByIdPort.findClienteOptionalById(idCliente)
                                          .map(cliente -> {
                                              return ResponseEntity.ok(modelMapper.map(cliente, ClienteResponse.class ));
                                          })
                                          .orElse(ResponseEntity.notFound().build());

    }

}
