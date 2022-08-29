package estudos.ecommerce.usuario.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true) private String login;
    private String senha;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER) private List<Perfil> perfis = new ArrayList<>();

    public Usuario(String login, String senha, String email) {

        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.perfis;
    }

    @Override
    public String getPassword() {

        return this.senha;
    }

    @Override
    public String getUsername() {

        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public void atribuiPerfil(Perfil perfil) {

        perfis.add(perfil);

    }
}
