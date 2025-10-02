package es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppinglistItemRepository extends JpaRepository<ShoppinglistItem, Long>{

    @Query(value = "SELECT sli.* FROM SHOPPINGLIST_ITEM sli WHERE sli.shoppinglist_id = :idShoppinglist AND sli.info_block = 0", nativeQuery = true)
    Optional<List<ShoppinglistItem>> findAllShoppinglistItemByShoppinglistIdAndInfoBlockFalse(@Param("idShoppinglist") Long id);

}
