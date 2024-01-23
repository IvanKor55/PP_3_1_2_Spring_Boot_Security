package ru.javamentor.springsecurity.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Authority")
    private String Authority;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    private List<User> user;

    public Long getId() {
        return id;
    }
    @Override
    public String getAuthority() {
        return this.Authority;
    }
    public void setAuthority(String authority) {
        Authority = authority;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
