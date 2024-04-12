package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Account;

public class UserDetailImpl implements UserDetails {
    private Integer accountId;
    private String accountName;
    private String password;
    private boolean isAccountNonLocked;
    private List<? extends GrantedAuthority> authorities;

    
	public UserDetailImpl(Integer accountId, String accountName, String password, boolean isAccountNonLocked,
			List<? extends GrantedAuthority> authorities) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.isAccountNonLocked = isAccountNonLocked;
		this.authorities = authorities;
	}
	
	public UserDetailImpl() {
	}

	public static UserDetailImpl convertAccountEntityToAccountDetail(Account account){

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole().getRoleName()));
        return new UserDetailImpl(
                account.getAccountId(),
                account.getAccountName(),
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
        return accountName;
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

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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