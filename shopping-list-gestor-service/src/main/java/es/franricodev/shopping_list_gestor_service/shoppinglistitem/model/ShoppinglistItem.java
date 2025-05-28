package es.franricodev.shopping_list_gestor_service.shoppinglistitem.model;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    @ManyToMany(mappedBy = "shoppinglistItems")
    private Set<Product> products;

    private CalculateSystem calculateSystem;

    @Column(name = "CALCULATED_PRICE")
    private Double calculatedPrice;

    @ManyToMany(mappedBy = "shoppinglistItems")
    private Set<ItemUnit> itemUnits;
}
