package estudos.ecommerce.databuilders;

import com.github.javafaker.Faker;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.Endereco;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClienteCreator {

    private static final Faker faker = new Faker();

    public static Cliente gerarUmCliente(String nome, String telefone, String cpf, LocalDate dataNasc) {

        return new Cliente(nome,
                           telefone,
                           cpf,
                           dataNasc,
                           new Endereco("Rua A", "10", "Brasil", "Brasil", "37775000", "casa"), null);
    }

    public static List<Cliente> geraClientes(int quantidade) {

        return quantidade > 0 ? IntStream.range(0, quantidade)
                                         .mapToObj(value -> umClienteAleatorio())
                                         .collect(Collectors.toList()) : Collections.emptyList();
    }

    public static Cliente umClienteAleatorio() {

        return new Cliente(faker.name()
                                .firstName(),
                           String.valueOf(new Random().nextInt()),
                           String.valueOf(faker.phoneNumber()),
                           LocalDate.now(),
                           new Endereco("Rua A", "10", "Brasil", "Brasil", "37775000", "casa"),
                           null);
    }
}
