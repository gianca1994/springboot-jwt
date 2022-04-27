package com.gianca1994.springbootjwt.configuration;

import com.gianca1994.springbootjwt.model.Role;
import com.gianca1994.springbootjwt.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {
    @Bean
    public CommandLineRunner autoSaveRoles(RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new Role(1L, "STANDARD"));
            roleRepository.save(new Role(2L, "ADMIN"));
        };
    }
}


