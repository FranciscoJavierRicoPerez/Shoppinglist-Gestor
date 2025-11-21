import axios from "axios";

async function updateIsActiveShoppinglist(id: number): Promise<boolean> {
  const response : boolean = import.meta.env.VITE_DATA_ACCESS === 'LOCAL' ? await InMemory() : await Api(id);
  return response;
}

async function InMemory(): Promise<boolean> {
  return true
}

async function Api(shoppinglistId: number) {
  const url = import.meta.env.VITE_API_URL_COMPUTER + 'api/shoppinglist/v1/' + shoppinglistId + '/update/isActive';
  const response = await axios.put(url);
  return response.data;
}

export { updateIsActiveShoppinglist }
