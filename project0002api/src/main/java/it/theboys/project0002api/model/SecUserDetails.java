package it.theboys.project0002api.model;

import it.theboys.project0002api.model.database.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecUserDetails implements UserDetails {
    private final User user;
    private final Collection<SimpleGrantedAuthority> authorities;

    public SecUserDetails(User user) {

        this.user = user;
        authorities = new ArrayList<>();
        user.getUserRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        if (user.getUserRole().size()>1) {
//            return true;
//        }
//        if (user.getRegisteredAt()+1000*60*60*5 - Instant.now().toEpochMilli()>0) {
//            return true;
//        } else{
//            return false;
//        }
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
