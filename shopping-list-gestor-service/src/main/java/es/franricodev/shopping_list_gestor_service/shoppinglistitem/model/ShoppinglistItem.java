package es.franricodev.shopping_list_gestor_service.shoppinglistitem.model;

import es.franricodev.shopping_list_gestor_service.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "SHOPPINGLIST_ITEM")
public class ShoppinglistItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ASSIGNATION_LIST_DATE")
    private Date assignationToListDate;

    @Column(name = "CALCULATED_PRICE")
    private Double calculatedPrice;

    @Column(name = "QUANTITY")
    private Integer quantity;



    // private Product product;

}
