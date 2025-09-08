package es.franricodev.shopping_list_gestor_service.wpItemUnit.model;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity
@Table(name = "WP_ITEMUNIT")
public class WpItemUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRICE_KG")
    private Double priceKg;

    @Column(name = "WEIGHT")
    private Double weight;

    @NotNull
    @Column(name = "INFO_BLOCK")
    private Boolean infoBlock;

    @OneToOne(mappedBy = "wpItemUnit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ItemUnit itemUnit;
}
