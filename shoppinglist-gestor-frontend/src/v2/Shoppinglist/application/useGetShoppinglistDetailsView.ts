import type { ShoppinglistViewDetails } from '@/v2/Shoppinglist/domain/ShoppinglistViewDetails'
import { getShoppinglistDetailsMetadata } from '@/v2/Shoppinglist/infrastructure/useCases/getShoppinglistDetailsMetadata'

export function useGetShoppinglistDetailsView() {
  async function refetch(idShoppinglist: number): Promise<ShoppinglistViewDetails> {
    return await getShoppinglistDetailsMetadata(idShoppinglist)
  }

  return {
    refetch,
  }
}
