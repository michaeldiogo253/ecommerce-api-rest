package estudos.ecommerce.config.controller;

import estudos.ecommerce.config.TokenService;
import estudos.ecommerce.config.controller.request.LoginRequest;
import estudos.ecommerce.config.controller.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest request) {



        UsernamePasswordAuthenticationToken dadosLogin = request.converter();

        try {

            Authentication authentication = authManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();
        }


    }

}
