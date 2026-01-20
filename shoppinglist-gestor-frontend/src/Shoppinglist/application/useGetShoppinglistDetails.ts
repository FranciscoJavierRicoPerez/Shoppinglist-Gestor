import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import { getShoppinglistDetails } from '@/Shoppinglist/infrastructure/useCases/getShoppinglistDetails'

export function useGetShoppinglistDetails() {
  async function refetch(): Promise<ShoppinglistDetails | null> {
    return await getShoppinglistDetails()
  }
  return { refetch }
}
