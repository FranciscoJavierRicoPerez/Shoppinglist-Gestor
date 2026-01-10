import type { ShoppinglistFilter } from '@/v2/Shoppinglist/domain/ShoppinglistFilter'
import { getShoppinglistFiltered } from '@/v2/Shoppinglist/infrastructure/useCases/getShoppinglistFiltered'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'

export function useGetShoppinglistFiltered() {
  async function refetch(filter: ShoppinglistFilter): Promise<ShoppinglistMetadata[]> {
    return await getShoppinglistFiltered(filter)
  }

  return { refetch }
}
