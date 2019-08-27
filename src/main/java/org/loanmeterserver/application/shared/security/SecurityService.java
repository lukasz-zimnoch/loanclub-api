package org.loanmeterserver.application.shared.security;

import org.loanmeterserver.domain.shared.vo.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SecurityService {

    public Mono<Account> getAuthenticatedAccount() {
        return getAuthentication()
                .map(this::toAccount);
    }

    private Mono<Authentication> getAuthentication() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication);
    }

    private Account toAccount(Authentication authentication) {
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;
        return new Account(jwt.getName());
    }
}