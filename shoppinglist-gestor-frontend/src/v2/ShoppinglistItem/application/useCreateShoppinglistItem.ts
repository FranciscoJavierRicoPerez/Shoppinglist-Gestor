import { createShoppinglistItem } from "@/ShoppinglistItem/infrastructure/useCases/createShoppinglistItem";
import { ResquestNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";
import { ResponseNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/ResponseNewShoppinglistItem";

export function useCreateShoppinglistItem() {
  async function refetch(data: ResquestNewShoppinglistItem, idShoppinglist: number) : Promise<ResponseNewShoppinglistItem> {
    return await createShoppinglistItem(data, idShoppinglist);
  }

  return { refetch };
}
