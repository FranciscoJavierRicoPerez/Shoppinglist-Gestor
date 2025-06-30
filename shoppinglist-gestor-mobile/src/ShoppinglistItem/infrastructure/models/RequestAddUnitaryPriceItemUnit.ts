export interface RequestAddUnitaryPriceItemUnit {
    shoppinglistItemId: number;
    quantity: number;
    price: number;
}

export const defaultRequestAddUnitaryPriceItemUnit = {
    shoppinglistItemId: -1,
    quantity: 0,
    price: 0
}