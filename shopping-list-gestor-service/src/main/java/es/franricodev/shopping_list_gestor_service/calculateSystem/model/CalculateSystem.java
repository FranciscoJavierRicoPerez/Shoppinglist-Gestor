package es.franricodev.shopping_list_gestor_service.calculateSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalculateSystem {
    UNITARY_PRICE(1L, "PRECIO UNITARIO"),
    WEIGHT_PRICE(2L, "PRECIO POR KG");
    public final Long id;
    public final String name;

}
