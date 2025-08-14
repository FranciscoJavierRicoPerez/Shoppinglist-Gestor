import { getAllItemsUnitUpGroupedByPrice } from "../infrastructure/useCases/getAllItemsUnitUpGroupedByPrice";
import { getAllItemUnitFromShoppinglistItem } from "../infrastructure/useCases/getAllItemUnitFromShoppinglistItem";

export function useGetAllItemUnitUpGroupedByPrice() {
  async function refetch(): Promise<ItemUnitUpGroupedByPriceList> {
    return await getAllItemsUnitUpGroupedByPrice();
  }
  return { refetch };
}
