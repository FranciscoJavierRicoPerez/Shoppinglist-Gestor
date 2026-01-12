import type { ShoppinglistTable } from '@/Shoppinglist/domain/ShoppinglistTable'
import { getShoppinglistTableMetadata } from '@/Shoppinglist/infrastructure/useCases/getShoppinglistTableMetadata'

export function useGetShoppinglistTableMetadata() {
  async function refetch(): Promise<ShoppinglistTable> {
    return await getShoppinglistTableMetadata()
  }
  return { refetch }
}
