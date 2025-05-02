import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import type { ResponseShoppinglist } from '../models/ResponseShoppinglist'
import responseGetShoppinglist from '@/Shoppinglist/infrastructure/mocks/responseGetAllShoppinglist.json'
import { createShoppinglistArray } from '@/Shoppinglist/infrastructure/services/ShoppinglistService'
import axios from 'axios';

async function getAllShoppinglist(): Promise<Shoppinglist[]> {
  const response : ResponseShoppinglist[] = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api();
  return createShoppinglistArray(response)
}

async function Api(): Promise<ResponseShoppinglist[]>{
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v1'
  const response = await axios.get('http://192.168.18.7:9000/api/shoppinglist/v1');
  return response.data
}

async function InMemory(): Promise<ResponseShoppinglist[]> {
  return responseGetShoppinglist as ResponseShoppinglist[]
}

export { getAllShoppinglist }
