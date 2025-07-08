package es.franricodev.shopping_list_gestor_service.wpItemUnit.repository;

import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WpItemUnitRepository extends JpaRepository<WpItemUnit, Long> {
}
