import { getAllItemsUnitUpGroupedByPrice } from "../infrastructure/useCases/getAllItemsUnitUpGroupedByPrice";
import { getAllItemUnitFromShoppinglistItem } from "../infrastructure/useCases/getAllItemUnitFromShoppinglistItem";

export function useGetAllItemUnitUpGroupedByPrice() {
  async function refetch(idShoppinglistItem : number): Promise<ItemUnitUpGroupedByPriceList> {
    return await getAllItemsUnitUpGroupedByPrice(idShoppinglistItem);
  }
  return { refetch };
}
