package estudos.ecommerce.carrinho.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ecommerce-api/carrinho")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListarCarrinhoDoClienteController {

    @PostMapping(path = "/detalhar-carrinho/{idCarrinho}")
    public ResponseEntity<String> execute(@PathVariable(value = "pathVar") String pathVar) {

        return new ResponseEntity<>(pathVar, HttpStatus.CREATED);
    }

}