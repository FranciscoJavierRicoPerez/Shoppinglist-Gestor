package es.franricodev.shopping_list_gestor_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenApi() {

        Server localServer = new Server();
        localServer.setUrl("http://localhost:9000");

        Info info = new Info();
        info.setTitle("SHOPPINGLIST-GESTOR-APP APPLICATION INTERFACE");

        return new OpenAPI().info(info).servers(List.of(localServer));
    }

    @Bean
    public GroupedOpenApi apiShoppinglist(){
        return GroupedOpenApi.builder()
                .group("api-shoppinglist")
                .pathsToMatch("/api/shoppinglist/**")
                .pathsToExclude("/dto/**", "/exception/**", "/mapper/**", "/messages/**", "/model", "/repository/**", "/service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiShoppinglistView(){
        return GroupedOpenApi.builder()
                .group("api-shoppinglist-view")
                .pathsToMatch("/api/shoppinglistview/**")
                .pathsToExclude("/dto/**", "/exception/**", "/mapper/**", "/messages/**", "/model", "/repository/**", "/service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiShoppinglistItem() {
        return GroupedOpenApi.builder()
                .group("api-shoppinglistitem")
                .pathsToMatch("/api/shoppinglistitem/**")
                .pathsToExclude("/dto/**", "/exception/**", "/mapper/**", "/messages/**", "/model", "/repository/**", "/service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiCalculateSystem() {
        return GroupedOpenApi.builder()
                .group("api-calculateSystem")
                .pathsToMatch("/api/calculateSystem/**")
                .pathsToExclude("/dto/**", "/exception/**", "/mapper/**", "/messages/**", "/model", "/repository/**", "/service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiProduct() {
        return GroupedOpenApi.builder()
                .group("api-product")
                .pathsToMatch("/api/product/**")
                .pathsToExclude("/dto/**", "/exception/**", "/mapper/**", "/messages/**", "/model", "/repository/**", "/service/**")
                .build();
    }

}
