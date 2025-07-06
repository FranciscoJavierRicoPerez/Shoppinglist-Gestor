package es.franricodev.shopping_list_gestor_service.itemUnit.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ITEM_UNIT")
public class ItemUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppinglist_item_id")
    private ShoppinglistItem shoppinglistItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wp_item_unit_id")
    private WpItemUnit wpItemUnit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "up_item_unit_id")
    private UpItemUnit upItemUnit;
}
