import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import type { RequestCreateShoppinglistItemForm } from '../models/request/RequestCreateShoppinglistItemForm'
import type { ResponseCreateShoppinglistItem } from '../models/response/ResponseCreateShoppinglistItem'
import responseCreateShoppinglistItem from '@/ShoppinglistItem/infrastructure/mocks/responseCreateShoppinglistItem.json'
import { createShoppinglistItemMetadataFromCreateForm } from '../services/shoppinglistItemService'
import axios from 'axios'
import type { CreateShoppinglistItemData } from '../models/dto/CreateShoppinglistItemData'
import { apiClient } from '@/Core/config/apiClient'
import { SHOPPINGLIST_ITEMS_ENDPOINTS } from '@/Core/config/config'

async function createShoppinglistItem(
  data: RequestCreateShoppinglistItemForm,
): Promise<CreateShoppinglistItemData> {
  const response: ResponseCreateShoppinglistItem =
    import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(data)
  const shoppinglistItemCreated: ShoppinglistItemMetadata =
    createShoppinglistItemMetadataFromCreateForm(response, data)
  return {
    created: response.created,
    shoppinglistItemMetadata: shoppinglistItemCreated,
  }
}

async function Api(
  data: RequestCreateShoppinglistItemForm,
): Promise<ResponseCreateShoppinglistItem> {
  const response = await apiClient.post(
    SHOPPINGLIST_ITEMS_ENDPOINTS.CREATE_SHOPPINGLIST_ITEM_METADATA(),
    data,
  )
  return response.data
}

async function InMemory(): Promise<ResponseCreateShoppinglistItem> {
  return responseCreateShoppinglistItem as ResponseCreateShoppinglistItem
}

export { createShoppinglistItem }
