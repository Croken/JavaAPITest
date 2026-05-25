package se.centernode.javaapitest;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "se.centernode.javaapitest.repository")
public class RepositoryConfig {

}
