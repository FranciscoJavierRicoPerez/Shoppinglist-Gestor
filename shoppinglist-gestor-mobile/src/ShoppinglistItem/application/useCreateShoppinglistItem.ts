import { createShoppinglistItem } from "@/ShoppinglistItem/infrastructure/useCases/createShoppinglistItem";
import { ResquestNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";

export function useCreateShoppinglistItem() {
  async function refetch(data: ResquestNewShoppinglistItem) {
    return await createShoppinglistItem(data);
  }

  return { refetch };
}
