package com.cy.tradingbot.domain.user;

import com.cy.tradingbot.domain.tradingBot.TradingBot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String authority;

    @Embedded
    private Credential credential = new Credential();

    public Credential getCredential() {
        return credential;
    }

    public void setAccessKey(String accessKey) {
        credential.setAccessKey(accessKey);
    }

    public void setSecretKey(String secretKey) {
        credential.setSecretKey(secretKey);
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<TradingBot> tradingBots;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public User(Long id, String username, String password, String authority, Credential credential, Set<TradingBot> tradingBots) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.credential = credential;
        this.tradingBots = tradingBots;
    }

    public Long getId() {
        return id;
    }
}
