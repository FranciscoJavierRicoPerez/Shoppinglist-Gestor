import { deleteShoppinglistData } from '@/Shoppinglist/infrastructure/useCases/deleteShoppinglistData.ts'

export function useDeleteShoppinglistData() {
  async function refetch(id: number): Promise<boolean> {
    return deleteShoppinglistData(id)
  }

  return { refetch }
}
