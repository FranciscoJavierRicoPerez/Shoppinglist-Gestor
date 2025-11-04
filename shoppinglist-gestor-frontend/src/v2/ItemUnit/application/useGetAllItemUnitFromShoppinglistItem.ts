import { ResponseGetAllItemUnitFromShoppinglistItem } from "../infrastructure/models/responses/ResponseGetAllItemUnitFromShoppinglistItem";
import { getAllItemUnitFromShoppinglistItem } from "../infrastructure/useCases/getAllItemUnitFromShoppinglistItem";

export function useGetAllItemUnitFromShoppinglistItem() {
  async function refetch(shoppinglistItemId: number) : Promise<ResponseGetAllItemUnitFromShoppinglistItem> {
    return await getAllItemUnitFromShoppinglistItem(shoppinglistItemId);
  }
  return { refetch };
}
