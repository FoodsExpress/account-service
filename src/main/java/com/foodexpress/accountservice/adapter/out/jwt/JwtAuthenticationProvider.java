package com.foodexpress.accountservice.adapter.out.jwt;

import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.AccountRole;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken)authentication;
        return processUserAuthentication(String.valueOf(authenticationToken.getPrincipal()), authenticationToken.getCredentials());
    }

    private Authentication processUserAuthentication(String principal, CredentialInfo credential) {
        try {
            Account account = getAccountDto(principal, credential);
            CredentialInfo credentialInfo = new CredentialInfo(account.password(), account.loginType());
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                new JwtAuthentication(account.accountId().getId(), account.email(), account.nickname()),
                credentialInfo,
                this.authorities(account.roles()));
            authenticationToken.setDetails(account);
            return authenticationToken;
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }

    public Account getAccountDto(String principal, CredentialInfo credential) {
        return null;
    }

    public Collection<? extends GrantedAuthority> authorities(Set<AccountRole> role) {
        return role.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
    }

}