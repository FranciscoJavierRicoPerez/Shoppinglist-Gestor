import axios from 'axios'
import type { DeleteShoppinglistItemData } from '../models/dto/DeleteShoppinglistItemData'
import type { ResponseDeleteShoppinglistItem } from '../models/response/ResponseDeleteShoppinglistItem'
import responseDeleteShoppinglistItem from '@/ShoppinglistItem/infrastructure/mocks/responseDeleteShoppinglistItem.json'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'
async function deleteShoppinglistItem(
  idShoppinglistItem: number,
): Promise<DeleteShoppinglistItemData> {
  const response: ResponseDeleteShoppinglistItem =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(idShoppinglistItem)
  return {
    message: response.message,
    delete: response.delete,
  }
}

async function Api(idShoppinglistItem: number): Promise<ResponseDeleteShoppinglistItem> {
  const response = await apiClient.delete(
    SHOPPINGLIST_ITEMS_ENDPOINTS.DELETE_V1(idShoppinglistItem),
  )
  return response.data
}

async function InMemory(): Promise<ResponseDeleteShoppinglistItem> {
  return responseDeleteShoppinglistItem as ResponseDeleteShoppinglistItem
}

export { deleteShoppinglistItem }
