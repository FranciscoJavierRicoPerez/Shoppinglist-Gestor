import axios from "axios"

async function deleteShoppinglistData(id: number): Promise<boolean> {
  const response : boolean = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(id);
  return response;
}

async function InMemory(): Promise<boolean> {
  return true
}

async function Api(shoppinglistId: number): Promise<boolean> {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v1/delete/' + shoppinglistId
  const response = await axios.delete(url);
  return response.data
}

export { deleteShoppinglistData }
