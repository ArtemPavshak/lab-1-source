package edu.pzks.opensource.config;


/*
  @author   george
  @project   open-source
  @class  AuditorAwareImpl
  @version  1.0.0 
  @since 15.04.25 - 10.57
*/

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(authentication.getName());
//        return Optional.of(System.getProperty("user.name"));
    }
}
