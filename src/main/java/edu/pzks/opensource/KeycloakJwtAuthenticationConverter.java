package edu.pzks.opensource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/*
  @author   george
  @project   open-source
  @class  JwtAuthenticationConverter
  @version  1.0.0 
  @since 26.03.25 - 11.44
*/


public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

//    @Value("${jwt.auth.converter.resource-id}")
//    private String resourse;

    private final String resourse = "open-rest-api";


    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {

        return new JwtAuthenticationToken(
                source,
                Stream.concat(
                                new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                                extractResourceRoles(source).stream())
                        .collect(toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        var resourceAccess = new HashMap<>(jwt.getClaim("resource_access"));
        var client = (Map<String, List<String>>) resourceAccess.get(resourse);

        var roles = client.get("roles");

        var result = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"
                        + role.replace("-", "_")))
                .collect(toSet());
        return result;
    }
}
