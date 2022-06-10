package estudos.ecommerce.cliente.adapter.in.web.request;

import estudos.ecommerce.cliente.domain.Endereco;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class AtualizaEnderecoClienteRequest {

    @NotNull String logradouro;
    @NotNull String numero;
    @NotNull String cidade;
    @NotNull String pais;
    @NotNull String cep;
    @NotNull String complemento;

    public Endereco toModel() {

        return new Endereco(this.logradouro,
                            this.numero,
                            this.getCidade(),
                            this.getPais(),
                            this.cep,
                            this.getComplemento());
    }
}
