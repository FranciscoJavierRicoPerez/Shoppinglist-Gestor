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
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(
            name = "itemUnit_shoppinglistItem",
            joinColumns = {@JoinColumn(name = "itemUnit_id")},
            inverseJoinColumns = {@JoinColumn(name = "shoppinglistItem_id")}
    )
    private Set<ShoppinglistItem> shoppinglistItems;
}
