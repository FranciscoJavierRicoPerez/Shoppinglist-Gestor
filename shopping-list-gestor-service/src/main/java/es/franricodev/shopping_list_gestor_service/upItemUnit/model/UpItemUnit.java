package es.franricodev.shopping_list_gestor_service.upItemUnit.model;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "UP_ITEMUNIT")
public class UpItemUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNITY_PRICE")
    private Double unityPrice;

    @NotNull
    @Column(name = "INFO_BLOCK")
    private Boolean infoBlock;

    @OneToOne(mappedBy = "upItemUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ItemUnit itemUnit;
}
