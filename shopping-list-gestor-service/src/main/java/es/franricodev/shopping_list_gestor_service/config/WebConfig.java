package es.franricodev.shopping_list_gestor_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: Implementar el cors en este fichero de configuracion
//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*registry.addMapping("/**")
                .allowedOrigins("http://192.168.19.7")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*");*/
    }
}
