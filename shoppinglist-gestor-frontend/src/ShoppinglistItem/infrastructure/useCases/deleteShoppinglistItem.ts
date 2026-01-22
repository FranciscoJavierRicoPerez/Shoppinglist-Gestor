import axios from 'axios'
import type { DeleteShoppinglistItemData } from '../models/dto/DeleteShoppinglistItemData'
import type { ResponseDeleteShoppinglistItem } from '../models/response/ResponseDeleteShoppinglistItem'
import responseDeleteShoppinglistItem from '@/ShoppinglistItem/infrastructure/mocks/responseDeleteShoppinglistItem.json'
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
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    '/api/shoppinglistitem/v1/' +
    idShoppinglistItem +
    'delete'
  const response = axios.delete(url)
  return (await response).data
}

async function InMemory(): Promise<ResponseDeleteShoppinglistItem> {
  return responseDeleteShoppinglistItem as ResponseDeleteShoppinglistItem
}

export { deleteShoppinglistItem }
