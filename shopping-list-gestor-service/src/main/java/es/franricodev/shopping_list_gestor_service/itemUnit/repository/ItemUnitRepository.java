package es.franricodev.shopping_list_gestor_service.itemUnit.repository;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUnitRepository extends JpaRepository<ItemUnit, Long> { }
