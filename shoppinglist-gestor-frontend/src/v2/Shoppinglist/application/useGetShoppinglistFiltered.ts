import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ShoppinglistFilter } from '@/Shoppinglist/domain/ShoppinglistFilter'
import { getShoppinglistFiltered } from '@/Shoppinglist/infrastructure/useCases/getShoppinglistFiltered'

export function useGetShoppinglistFiltered() {
  async function refetch(filter: ShoppinglistFilter): Promise<Shoppinglist[]> {
    return await getShoppinglistFiltered(filter)
  }

  return { refetch }
}
