import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '../models/ResponseShoppinglist'
import responseGetShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetAllShoppinglist.json'
import { createShoppinglistArray } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'

async function getAllShoppinglist(): Promise<Shoppinglist[]> {
  console.log(import.meta.env.MODE)
  const response : ResponseShoppinglist[] = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api();
  return createShoppinglistArray(response)
}

async function Api(): Promise<ResponseShoppinglist[]>{
  console.log('API');
  return [];
}

async function InMemory(): Promise<ResponseShoppinglist[]> {
  return responseGetShoppinglist as ResponseShoppinglist[]
}

export { getAllShoppinglist }
