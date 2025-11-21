import { reduceItemUpQuantity } from "@/ItemUnit/infrastructure/useCases/reduceItemUpQuantity";

export function useReduceQuantityToItemUp() {
  async function refetch(
    idShoppinglistItem: number,
    itemPrice: number
  ): Promise<void> {
    return await reduceItemUpQuantity(idShoppinglistItem, itemPrice);
  }

  return { refetch };
}
