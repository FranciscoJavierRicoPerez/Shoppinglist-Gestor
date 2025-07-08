package es.franricodev.shopping_list_gestor_service.upItemUnit.repository;

import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpItemUnitRepository extends JpaRepository<UpItemUnit, Long> {
}
