package es.franricodev.shopping_list_gestor_service.shoppinglist.model;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ApiShoppinglistV3Constants.SHOPPINGLIST)
public class Shoppinglist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ApiShoppinglistV3Constants.CREATION_DATE)
    private LocalDate creationDate;

    @NotNull
    @Column(name = ApiShoppinglistV3Constants.CODE)
    private String code;

    @Column(name = ApiShoppinglistV3Constants.CLOSE_DATE)
    private LocalDate closeDate;

    @NotNull
    @Column(name = ApiShoppinglistV3Constants.TOTAL_PRICE)
    private Double totalPrice;

    @NotNull
    @Column(name = ApiShoppinglistV3Constants.IS_ACTIVE)
    private Boolean isActive;

    @NotNull
    @Column(name = ApiShoppinglistV3Constants.INFO_BLOCK)
    private Boolean infoBlock;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = ApiShoppinglistV3Constants.SHOPPINGLIST_ID)
    private List<ShoppinglistItem> items = new ArrayList<>();
}
