package es.franricodev.shopping_list_gestor_service.priceCalculator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightPriceCalculator extends GenericPriceCalculator{

    private Double productWeight; // El peso del producto

    private Double priceWeight; // El precio del producto por cada Kg

    private Double calculatedPrice; // El precio del producto calculado

    public WeightPriceCalculator(Double productWeight, Double priceWeight) {
        this.productWeight = productWeight;
        this.priceWeight = priceWeight;
    }

    public void calculatePrice() {
        calculatedPrice = productWeight * priceWeight;
    }


}
