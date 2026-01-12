import type { ShoppinglistFilter } from '@/Shoppinglist/domain/ShoppinglistFilter'
import { getShoppinglistFiltered } from '@/Shoppinglist/infrastructure/useCases/getShoppinglistFiltered'
import type { ShoppinglistTable } from '../domain/ShoppinglistTable'

export function useGetShoppinglistFiltered() {
  async function refetch(filter: ShoppinglistFilter): Promise<ShoppinglistTable> {
    return await getShoppinglistFiltered(filter)
  }

  return { refetch }
}
