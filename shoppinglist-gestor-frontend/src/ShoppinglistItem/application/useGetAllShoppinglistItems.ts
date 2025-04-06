import { getAllShoppinglistItems } from '@/ShoppinglistItem/infrastructure/useCases/getAllShoppinglistItems'

export function useGetAllShoppinglistItems() {
  async function refetch() {
    return await getAllShoppinglistItems()
  }

  return { refetch }
}
