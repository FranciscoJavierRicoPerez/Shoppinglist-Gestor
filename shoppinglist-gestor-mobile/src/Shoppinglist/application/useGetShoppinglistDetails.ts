import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import { getShoppinglistDetails } from '@/Shoppinglist/infrastructure/useCases/getShoppinglistDetails'

export function useGetShoppinglistDetails() {
  async function refetch(id: number): Promise<ShoppinglistDetails> {
    return await getShoppinglistDetails(id)
  }
  return { refetch }
}
