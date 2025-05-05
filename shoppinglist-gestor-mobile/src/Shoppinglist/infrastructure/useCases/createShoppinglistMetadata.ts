import type { ResponseShoppinglist } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglist'
import responseCreateShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseCreateShoppinglist.json'
import { createShoppinglist } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import axios from 'axios';
async function createShoppinglistMetadata(): Promise<Shoppinglist> {
  const response : ResponseShoppinglist = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api();
  return createShoppinglist(response)
}

async function Api() : Promise<ResponseShoppinglist> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v1/create'
  const response = await axios.post(url, {
    code: 'LISTA_COMPRA_',
    totalPrice: 0,
    isActive: true
  });
  return response.data;
}

async function InMemory(): Promise<ResponseShoppinglist> {
  return responseCreateShoppinglist as ResponseShoppinglist
}

export { createShoppinglistMetadata }
