package com.cinar.readingisgood.base.config;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableMongoAuditing
public class DatabaseAuditing implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder
        .getContext()
        .getAuthentication();
    String uname = "System";
    if (authentication != null) {
      uname = authentication.getName();
    }
    return Optional.of(uname);
  }
}