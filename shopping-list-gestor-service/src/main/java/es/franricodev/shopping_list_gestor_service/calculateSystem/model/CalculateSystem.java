package es.franricodev.shopping_list_gestor_service.calculateSystem.model;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "calculateSystem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppinglistItem> items = new ArrayList<>();

}
