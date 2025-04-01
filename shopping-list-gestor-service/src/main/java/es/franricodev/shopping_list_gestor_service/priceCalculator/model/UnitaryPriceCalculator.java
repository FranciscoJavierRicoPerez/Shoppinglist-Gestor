package es.franricodev.shopping_list_gestor_service.priceCalculator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitaryPriceCalculator extends GenericPriceCalculator{
    private Double unitaryPrice;
}
