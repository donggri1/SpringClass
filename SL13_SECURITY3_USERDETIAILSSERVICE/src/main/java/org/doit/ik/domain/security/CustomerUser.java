package org.doit.ik.domain.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.doit.ik.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomerUser extends User{

	private MemberVO member;
	
	public CustomerUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	public CustomerUser(MemberVO member) {
		super(member.getId(),member.getPwd()
				// List<Auth> ->
				// Collection<? extends GrantedAuthority>authorities
				,member.getAuthList().stream().map(auth->new SimpleGrantedAuthority(auth.getAuthority()))
				.collect(Collectors.toList())				
				);
		this.member=member; // 반드시 입력 잊지않기
	}//customer

}//class
