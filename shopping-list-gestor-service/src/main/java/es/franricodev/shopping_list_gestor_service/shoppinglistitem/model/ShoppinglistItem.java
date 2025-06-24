package es.franricodev.shopping_list_gestor_service.shoppinglistitem.model;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "SHOPPINGLIST_ITEM")
public class ShoppinglistItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ASSIGNATION_LIST_DATE")
    private Date assignationToListDate;

    @ManyToMany(mappedBy = "shoppinglistItems", cascade = {CascadeType.ALL})
    private Set<Product> products = new HashSet<>();

    @Column(name = "CALCULATED_PRICE")
    private Double calculatedPrice;

    @OneToMany(mappedBy = "shoppinglistItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemUnit> itemUnitList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calculate_system_id")
    private CalculateSystem calculateSystem;
}
