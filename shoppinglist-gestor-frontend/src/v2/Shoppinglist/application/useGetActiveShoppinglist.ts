import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { getActiveShoppinglist } from '@/Shoppinglist/infrastructure/useCases/getActiveShoppinglist'
export function useGetActiveShoppinglist() {
  async function refetch(): Promise<Shoppinglist[]> {
    return await getActiveShoppinglist()
  }
  return { refetch }
}
