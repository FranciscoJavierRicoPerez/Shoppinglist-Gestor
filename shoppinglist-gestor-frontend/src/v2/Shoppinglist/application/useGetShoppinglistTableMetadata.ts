import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
import { getShoppinglistTableMetadata } from '@/v2/Shoppinglist/infrastructure/useCases/getShoppinglistTableMetadata'

export function useGetShoppinglistTableMetadata() {
  async function refetch(): Promise<ShoppinglistMetadata[]> {
    return getShoppinglistTableMetadata()
  }
  return {
    refetch,
  }
}
