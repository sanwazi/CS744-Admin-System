package com.example.EMR_Admin.authentication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.authentication.dao.UserAuthenticationDao;
import com.example.EMR_Admin.authentication.data.Physician;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserAuthenticationDao uaDao;

	public CustomUserDetailsService() {
		System.out.println("CustomUserDetailsService has been made");
	}

	@Override
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		UserDetails ud = getUser(account);
		return ud;
	}

	private UserDetails getUser(String account)
			throws UsernameNotFoundException {
		List<Physician> userList = uaDao.listUser();
		Physician currentUser = null;
		System.out.println(userList.size());
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getAccount().equals(account)) {
				System.out.println(account);
				currentUser = userList.get(i);
			}
		}
		if (currentUser == null) {
			System.out.println("No User Exist");
			return null;
		}
		System.out.println("ROLE_PHYSICIAN");
		UserDetails user = new User(currentUser.getAccount(),
				currentUser.getPassword(), true, true, true, true,
				this.getAuthorities("PHYSICIAN"));

		return user;
	}

	/**
	 * get authorities of user
	 * 
	 * @param accountType
	 * @return
	 */
	private Collection<GrantedAuthority> getAuthorities(String accountType) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(3);

		if (accountType.equals("PHYSICIAN")) {
			authList.add(new GrantedAuthorityImpl("ROLE_PHYSICIAN"));
		}

		if (accountType.equals("ADMIN")) {
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}

		return authList;
	}

	public static UserDetails currentUserDetails() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			return (UserDetails) (principal instanceof UserDetails ? principal
					: null);
		}
		return null;
	}

}