import { addItemUpQuantity } from "@/ItemUnit/infrastructure/useCases/addItemUpQuantity";

export function useAddQuantityToItemUp() {
  async function refetch(
    idShoppinglistItem: number,
    itemPrice: number
  ): Promise<void> {
    return await addItemUpQuantity(idShoppinglistItem, itemPrice);
  }

  return { refetch };
}
