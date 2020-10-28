package com.root.Security.UserDetail;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.root.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class UserDetailCustomer  implements UserDetails{
	private static final long serialVersionUID = 1L;
	private Long id;
	 
    private String username;
 
    private String password;
 
    private Collection<? extends GrantedAuthority> roles;
    
    public static UserDetailCustomer build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
 
        return new UserDetailCustomer(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        UserDetailCustomer user = (UserDetailCustomer) o;
        return Objects.equals(id, user.id);
    }
 
    @Override
    public int hashCode() {
        return super.hashCode();
    }

	public UserDetailCustomer(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
}
