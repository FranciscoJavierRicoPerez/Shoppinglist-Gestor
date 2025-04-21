import { deleteShoppinglistData } from '@/Shoppinglist/infrastructure/useCases/deleteShoppinglistData'

export function useDeleteShoppinglistData() {
  async function refetch(id: number): Promise<boolean> {
    return deleteShoppinglistData(id)
  }

  return { refetch }
}
