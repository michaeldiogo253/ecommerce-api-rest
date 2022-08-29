package estudos.ecommerce.administrador.adapter.in.web;

import estudos.ecommerce.administrador.adapter.in.web.request.CadastrarAdministradorRequest;
import estudos.ecommerce.administrador.adapter.in.web.response.AdministradorDoSistemaResponse;
import estudos.ecommerce.administrador.application.CadastrarAdministradorDoSistemaCommand;
import estudos.ecommerce.administrador.application.CadastrarAdministradorDoSistemaUseCase;
import estudos.ecommerce.administrador.domain.AdministradorDoSistema;
import estudos.ecommerce.cliente.domain.DadosPessoais;
import estudos.ecommerce.util.data.ConversorDeDatas;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;

@RequestMapping("/ecommerce-api/admin")
@RestController
@RequiredArgsConstructor
public class CadastrarAdministradorDoSistemaController {

    private final CadastrarAdministradorDoSistemaUseCase useCase;

    @PostMapping("/cadastrar")
    public ResponseEntity<AdministradorDoSistemaResponse> cadastrarAdministrador(
            @RequestBody @Valid CadastrarAdministradorRequest request, UriComponentsBuilder uriBuilder) {

        LocalDate dataConvertida = ConversorDeDatas.converteDataStringParaLocalDate(request.getDataNasc());

        AdministradorDoSistema administradorDoSistema = useCase.execute(CadastrarAdministradorDoSistemaCommand.builder()
                                                                                                              .dadosPessoais(
                                                                                                                      new DadosPessoais(
                                                                                                                              request.getNome(),
                                                                                                                              request.getCpf(),
                                                                                                                              request.getTelefone(),
                                                                                                                              dataConvertida))
                                                                                                              .usuario(
                                                                                                                      request.getUsuarioRequest()
                                                                                                                             .toModel())
                                                                                                              .build());

        return ResponseEntity.ok(new AdministradorDoSistemaResponse(administradorDoSistema));
    }
}
