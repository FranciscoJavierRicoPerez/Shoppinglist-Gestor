package es.franricodev.shopping_list_gestor_service.calculateSystem.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "CALCULATE_SYSTEM")
public class CalculateSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "calculate_system_shoppinglist_item",
            joinColumns = {@JoinColumn(name = "calculate_system_id")},
            inverseJoinColumns = {@JoinColumn(name = "shoppinglist_item_id")}
    )
    private Set<ShoppinglistItem> shoppinglistItems;
}
