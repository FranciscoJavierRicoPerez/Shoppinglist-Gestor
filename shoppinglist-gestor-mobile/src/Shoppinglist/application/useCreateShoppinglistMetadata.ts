import type { Shoppinglist } from "@/Shoppinglist/domain/Shoppinglist";
import { createShoppinglistMetadata } from "@/Shoppinglist/infrastructure/useCases/createShoppinglistMetadata";

export function useCreateShoppinglistMetadata() {
  let loading: boolean = false;

  async function refetch(): Promise<Shoppinglist> {
    loading = true;
    let response = await createShoppinglistMetadata();
    loading = false;
    return response;
  }
  return { refetch, loading };
}
