import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ENDPOINTS } from '@/Core/config/config'

async function updateIsActiveShoppinglist(idShoppinglist: number): Promise<boolean> {
  const response: boolean =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglist)
  return response
}

async function InMemory(): Promise<boolean> {
  return true
}

async function Api(idShoppinglist: number): Promise<boolean> {
  const response = await apiClient.put(SHOPPINGLIST_ENDPOINTS.UPDATE_IS_ACTIVE_V1(idShoppinglist))
  return response.data
}

export { updateIsActiveShoppinglist }
