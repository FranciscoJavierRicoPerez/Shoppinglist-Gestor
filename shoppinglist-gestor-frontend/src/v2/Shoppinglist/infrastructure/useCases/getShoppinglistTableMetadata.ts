import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
import type { ResponseGetShoppinglistTableMetadata } from '@/v2/Shoppinglist/infrastructure/models/ResponseGetShoppinglistTableMetadata'
import responseGetShoppinglistTableMetadata from '@/v2/Shoppinglist/infrastructure/mocks/responseGetShoppinglistTableMetadata.json'
import axios from 'axios'
import { createShoppinglistMetadataList } from '@/v2/Shoppinglist/infrastructure/services/ShoppinglistService'

async function getShoppinglistTableMetadata(): Promise<ShoppinglistMetadata[]> {
  const response = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api()
  return createShoppinglistMetadataList(response)
}

async function Api(): Promise<ResponseGetShoppinglistTableMetadata> {
  const url = ''
  const response = await axios.get(url)
  return response.data
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetShoppinglistTableMetadata as ResponseGetShoppinglistTableMetadata
}

export { getShoppinglistTableMetadata }
