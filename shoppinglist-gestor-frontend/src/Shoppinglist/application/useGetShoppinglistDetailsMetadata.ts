import type { ShoppinglistDetailsMetadata } from '../domain/ShoppinglistDetailsMetadata'
import { getShoppinglistDetailsMetadata } from '../infrastructure/useCases/getShoppinglistDetailsMetadata'

export function useGetShoppinglistDetailsMetadata() {
  async function refetch(idShoppinglist: number): Promise<ShoppinglistDetailsMetadata> {
    return await getShoppinglistDetailsMetadata(idShoppinglist)
  }

  return {
    refetch,
  }
}
