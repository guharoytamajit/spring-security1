package demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.entity.Account;
import demo.entity.Role;
import demo.repo.AccountDao;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountDao accountDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Account account = accountDao.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthority(account.getRoles());
		return buildUserForAuthentication(account, authorities);
	}

	private User buildUserForAuthentication(Account account,
			List<GrantedAuthority> authorities) {
		// User(String username, String password, boolean enabled, boolean
		// accountNonExpired,
		// boolean credentialsNonExpired, boolean accountNonLocked, Collection<?
		// extends GrantedAuthority> authorities)
		return new User(account.getUsername(), account.getPassword(),
				account.isEnabled(), !account.isExpired(),
				!account.isCredentialsExpired(), !account.isLocked(),
				authorities);
	}

	private List<GrantedAuthority> buildAuthority(Set<Role> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getCode()));
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
				setAuths);

		return authorities;
	}

}
