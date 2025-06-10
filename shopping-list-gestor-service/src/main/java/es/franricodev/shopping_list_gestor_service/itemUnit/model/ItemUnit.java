package es.franricodev.shopping_list_gestor_service.itemUnit.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
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

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "item_unit_shoppinglist_item",
            joinColumns = {@JoinColumn(name = "item_unit_id")},
            inverseJoinColumns = {@JoinColumn(name = "shoppinglist_item_id")}
    )
    private Set<ShoppinglistItem> shoppinglistItems;
}
