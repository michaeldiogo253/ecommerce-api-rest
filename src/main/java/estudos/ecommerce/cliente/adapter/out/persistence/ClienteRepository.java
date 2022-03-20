package estudos.ecommerce.cliente.adapter.out.persistence;

import estudos.ecommerce.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
