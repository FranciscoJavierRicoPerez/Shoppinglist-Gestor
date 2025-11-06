import { createShoppinglistMetadataList } from '@/v2/Shoppinglist/infrastructure/services/ShoppinglistService'
import type { ShoppinglistFilter } from '@/v2/Shoppinglist/domain/ShoppinglistFilter'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
import type { ResponseGetShoppinglistTableMetadata } from '@/v2/Shoppinglist/infrastructure/models/ResponseGetShoppinglistTableMetadata'
import responseGetShoppinglistTableMetadata from '@/v2/Shoppinglist/infrastructure/mocks/responseGetShoppinglistTableMetadata.json'

async function getShoppinglistFiltered(data: ShoppinglistFilter): Promise<ShoppinglistMetadata[]> {
  return createShoppinglistMetadataList(await InMemory())
}

async function InMemory(): Promise<ResponseGetShoppinglistTableMetadata> {
  return responseGetShoppinglistTableMetadata as ResponseGetShoppinglistTableMetadata
}

export { getShoppinglistFiltered }
