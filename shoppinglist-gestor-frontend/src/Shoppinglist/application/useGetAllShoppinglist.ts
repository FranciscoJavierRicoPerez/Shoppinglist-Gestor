import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { getAllShoppinglist } from '@/Shoppinglist/infrastructure/useCases/getAllShoppinglist'
export function useGetAllShoppinglist() {
  async function refetch(): Promise<Shoppinglist[]> {
    return await getAllShoppinglist()
  }
  return { refetch }
}
