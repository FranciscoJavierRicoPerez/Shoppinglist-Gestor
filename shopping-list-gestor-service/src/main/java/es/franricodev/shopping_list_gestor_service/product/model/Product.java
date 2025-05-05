package es.franricodev.shopping_list_gestor_service.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @NotNull
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @NotNull
    @Column(name = "LAST_BUY_DATE")
    private Date lastBuyDate;

    @Null
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

}
