package es.franricodev.shopping_list_gestor_service.calculateSystem.repository;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculateSystemRepository extends JpaRepository<CalculateSystem, Long> {
}
