package es.franricodev.shopping_list_gestor_service.shoppinglist.repository;

import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppinglistRepository extends JpaRepository<Shoppinglist, Long> {

    Optional<Shoppinglist> IsActiveTrue();

}
