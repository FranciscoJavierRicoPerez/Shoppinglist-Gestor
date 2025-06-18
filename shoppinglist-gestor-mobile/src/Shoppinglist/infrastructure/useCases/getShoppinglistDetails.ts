import type { ShoppinglistDetails } from '@/Shoppinglist/domain/ShoppinglistDetails'
import type { ResponseShoppinglistDetails } from '@/Shoppinglist/infrastructure/models/ResponseShoppinglistDetails'
import responseGetShoppinglistDetails from '@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetails.json'
import { createShoppinglistDetails } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import axios from 'axios'

async function getShoppinglistDetails(id: number): Promise<ShoppinglistDetails> {
  const response : ResponseShoppinglistDetails = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(id); 
  return createShoppinglistDetails(response)
}

async function Api(id: number): Promise<ResponseShoppinglistDetails> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v1/' + id + '/details';
  const response = await axios.get(url);
  return response.data;
}

async function InMemory(): Promise<ResponseShoppinglistDetails> {
  return responseGetShoppinglistDetails as ResponseShoppinglistDetails
}

export { getShoppinglistDetails }
