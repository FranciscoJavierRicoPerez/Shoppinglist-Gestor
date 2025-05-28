package es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppinglistItemRepository extends JpaRepository<ShoppinglistItem, Long>{
}
