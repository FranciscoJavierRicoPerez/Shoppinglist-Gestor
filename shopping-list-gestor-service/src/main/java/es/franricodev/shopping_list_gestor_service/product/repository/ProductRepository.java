package es.franricodev.shopping_list_gestor_service.product.repository;

import es.franricodev.shopping_list_gestor_service.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
