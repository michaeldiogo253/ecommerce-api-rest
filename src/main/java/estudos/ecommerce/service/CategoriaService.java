package estudos.ecommerce.service;

import estudos.ecommerce.controller.request.CategoriaRequest;
import estudos.ecommerce.controller.response.CategoriaResponse;
import estudos.ecommerce.model.Categoria;
import estudos.ecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public ResponseEntity<?> cadastrarCategoria(CategoriaRequest request, UriComponentsBuilder uriBuilder) {

        try {
            Categoria categoria = new Categoria(request.getNome());
            categoriaRepository.save(categoria);

            URI uri = uriBuilder.path("ecommerce-api/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
            return ResponseEntity.created(uri).body(new CategoriaResponse(categoria.getId(), categoria.getNome()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            return ResponseEntity.ok(new CategoriaResponse(
                    categoriaBuscada.get().getId(), categoriaBuscada.get().getNome()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
    }

    public ResponseEntity<List<CategoriaResponse>> listarTodasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAllByOrderByIdAsc();
        return ResponseEntity.ok().body(CategoriaResponse.from(categorias));
    }

    public ResponseEntity<?> alterarCategoria(Long id, CategoriaRequest request) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            System.out.println("entrou no if");
            categoriaBuscada.get().alteraNomeCategoria(request.getNome());
            System.out.println("nome alterado =" + categoriaBuscada.get().getNome());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
    }

    public ResponseEntity<?> deletarCategoria(Long id) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
    }

}
