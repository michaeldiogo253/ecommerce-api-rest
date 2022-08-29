package estudos.ecommerce.administrador.adapter.out.persistence;

import estudos.ecommerce.administrador.application.port.SalvarAdministradorSistemaPort;
import estudos.ecommerce.administrador.domain.AdministradorDoSistema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministradorDoSistemaPersistenceAdapter implements SalvarAdministradorSistemaPort {

    private final AdministradorDoSistemaRepository administradorDoSistemaRepository;

    @Override
    public AdministradorDoSistema salvarAdministradorDoSistema(AdministradorDoSistema administradorDoSistema) {

        return administradorDoSistemaRepository.save(administradorDoSistema);
    }
}
