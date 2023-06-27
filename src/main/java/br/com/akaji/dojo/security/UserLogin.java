package br.com.akaji.dojo.security;

import br.com.akaji.dojo.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
public class UserLogin implements UserDetails {
    @Id
    @Column(length = 50, nullable = false, name = "username")
    private String userName;

    @Column(length = 500, nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default false")
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_login_id", referencedColumnName = "id", nullable = false)
    private User user = new User();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();


    public UserLogin(Long id, String name, UserLogin userLogin) {
        this.user = new User();
        this.userName = userLogin.getUsername();
        this.password = userLogin.getPassword();
        this.enabled = userLogin.getEnabled();
        this.initAuthorities(userLogin);
        this.user.setId(id);
        this.user.setName(name);
    }

    private void initAuthorities(UserLogin userLogin) {
        if (userLogin != null && userLogin.getAuthorities() != null) {
            this.authorities = (List) userLogin.getAuthorities();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return this.enabled;
    }

}
