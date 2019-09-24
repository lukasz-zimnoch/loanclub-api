package org.loanclub.api.shared.security;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AwsCognitoJwtAuthenticationConverter extends JwtAuthenticationConverter {

    private static final String COGNITO_GROUPS_CLAIM = "cognito:groups";

    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = super.extractAuthorities(jwt);
        grantedAuthorities.addAll(extractCognitoGroups(jwt));
        return grantedAuthorities;
    }

    @SuppressWarnings("unchecked")
    private List<GrantedAuthority> extractCognitoGroups(Jwt jwt) {
        return CollectionUtils.emptyIfNull((List<String>) jwt.getClaims().get(COGNITO_GROUPS_CLAIM))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
