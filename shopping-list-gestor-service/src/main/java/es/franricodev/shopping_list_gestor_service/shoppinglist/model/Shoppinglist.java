package es.franricodev.shopping_list_gestor_service.shoppinglist.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SHOPPINGLIST")
public class Shoppinglist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @Null
    @Column(name = "CLOSE_DATE")
    private Date closeDate;

    @NotNull
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @NotNull
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppinglist_id")
    private List<ShoppinglistItem> items;

}
