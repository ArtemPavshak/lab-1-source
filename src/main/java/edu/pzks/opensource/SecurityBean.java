package edu.pzks.opensource;


/*
  @author   george
  @project   open-source
  @class  SecurityBean
  @version  1.0.0 
  @since 26.03.25 - 12.33
*/


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


import java.util.Collection;
import java.util.Map;

@Configuration
public class SecurityBean {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private  String issuerUri;

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return JwtDecoders.fromOidcIssuerLocation(issuerUri);
    }



}
