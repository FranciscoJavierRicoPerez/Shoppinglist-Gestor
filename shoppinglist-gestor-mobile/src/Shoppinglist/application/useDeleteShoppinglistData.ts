import { deleteShoppinglistData } from '@/Shoppinglist/infrastructure/useCases/deleteShoppinglistData.ts'

export function useDeleteShoppinglistData() {
  async function refetch(id: number): Promise<boolean> {
    debugger
    return deleteShoppinglistData(id)
  }

  return { refetch }
}
