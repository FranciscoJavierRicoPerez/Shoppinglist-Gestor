import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { getNoActiveShoppinglist } from '@/Shoppinglist/infrastructure/useCases/getNoActiveShoppinglist'
export function useGetNoActiveShoppinglist() {
  async function refetch(): Promise<Shoppinglist[]> {
    return await getNoActiveShoppinglist()
  }
  return { refetch }
}
