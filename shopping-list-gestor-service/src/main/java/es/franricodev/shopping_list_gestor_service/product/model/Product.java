package es.franricodev.shopping_list_gestor_service.product.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(
            name = "product_shoppinglistitem",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "shoppinglistitem_id")}
    )
    private Set<ShoppinglistItem> shoppinglistItems;

}
