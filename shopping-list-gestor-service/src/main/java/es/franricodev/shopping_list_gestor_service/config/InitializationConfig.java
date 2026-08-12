package es.franricodev.shopping_list_gestor_service.config;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.repository.CalculateSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class InitializationConfig {

    @Autowired
    private CalculateSystemRepository calculateSystemRepository;

    @Bean
    void verifyCalculateSystems() {
        log.info("Verification if calculatesystems exists in the database");
        List<CalculateSystem> calculateSystemList = calculateSystemRepository.findAll();
        if (calculateSystemList.isEmpty()) {
            log.info("Adding calculate system UP");
            CalculateSystem csUp = new CalculateSystem();
            csUp.setId(1L);
            csUp.setCode("UP");
            csUp.setName("Precio Unitario");
            calculateSystemRepository.save(csUp);
            log.info("Adding calculate system WP");
            CalculateSystem csWp = new CalculateSystem();
            csWp.setId(2L);
            csWp.setCode("WP");
            csWp.setName("Precio KG/€");
        }
    }


}
