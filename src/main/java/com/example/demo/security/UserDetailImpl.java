package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Account;

public class UserDetailImpl implements UserDetails {
    private Integer id;
    private String name;
    private String password;
    private boolean isAccountNonLocked;
    private List<? extends GrantedAuthority> authorities;

    
	public UserDetailImpl(Integer id, String name, String password, boolean isAccountNonLocked,
			List<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isAccountNonLocked = isAccountNonLocked;
		this.authorities = authorities;
	}
	
	public UserDetailImpl() {
	}

	public static UserDetailImpl convertAccountEntityToAccountDetail(Account account){

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole().getRoleName()));
        return new UserDetailImpl(
                account.getId(),
                account.getName(),
                account.getPassword(),
                !account.getIsDeleted(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}