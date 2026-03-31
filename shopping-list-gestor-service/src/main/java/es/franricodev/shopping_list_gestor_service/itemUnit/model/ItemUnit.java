package es.franricodev.shopping_list_gestor_service.itemUnit.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shoppinglist_item_id")
    private ShoppinglistItem shoppinglistItem;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "wp_item_unit_id")
    private WpItemUnit wpItemUnit;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unit_id")
    private List<UpItemUnit> upItemUnitList = new ArrayList<>();

    public boolean isWpItem() {
        return wpItemUnit != null;
    }
    public boolean isUpItem() {
        return upItemUnitList != null && !upItemUnitList.isEmpty();
    }

    @NotNull
    @Column(name = "INFO_BLOCK")
    private Boolean infoBlock;

}
