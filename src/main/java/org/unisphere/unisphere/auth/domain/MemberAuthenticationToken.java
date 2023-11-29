package org.unisphere.unisphere.auth.domain;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class MemberAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;

	public MemberAuthenticationToken(
			Object principal,
			Collection<? extends GrantedAuthority> authorities
	) {
		super(authorities);
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return principal;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}