package com.file_sharing.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

<<<<<<< HEAD
@Configuration // Indicates that this class contains configuration settings
public class AppConfig {

    /**
     * Configures a ModelMapper bean to be used for mapping between DTOs and entities.
     * ModelMapper will be automatically available in the Spring context for dependency injection.
     *
     * @return a new instance of ModelMapper
     */
=======
@Configuration
public class AppConfig {
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
