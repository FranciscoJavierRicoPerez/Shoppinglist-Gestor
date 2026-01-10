export interface RequestAddWeightPriceItemUnit {
  shoppinglistItemId: number;
  priceKg: number;
  weight: number;
}

export const defaultRequestAddWeightPriceItemUnit = {
  shoppinglistItemId: -1,
  priceKg: 0,
  weight: 0,
};
