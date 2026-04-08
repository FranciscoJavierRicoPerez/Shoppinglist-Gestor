package es.franricodev.shopping_list_gestor_service.itemUnit.repository;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemUnitRepository extends JpaRepository<ItemUnit, Long> {
    Optional<List<ItemUnit>> findAllByShoppinglistItemAndInfoBlockFalse(ShoppinglistItem shoppinglistItem);

    Optional<ItemUnit> findByIdAndInfoBlockFalse(Long idItemUnit);
}
