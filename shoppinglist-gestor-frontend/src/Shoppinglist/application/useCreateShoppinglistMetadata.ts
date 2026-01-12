import { createShoppinglistMetadata } from '@/Shoppinglist/infrastructure/useCases/createShoppinglistMetadata'
import type { ShoppinglistMetadata } from '../domain/ShoppinglistMetadata'

export function useCreateShoppinglistMetadata() {
  async function refetch(): Promise<ShoppinglistMetadata> {
    return await createShoppinglistMetadata()
  }
  return { refetch }
}
