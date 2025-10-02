import { ResquestNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";
import axios from "axios";
import { ResponseNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/ResponseNewShoppinglistItem";

async function Api(data: ResquestNewShoppinglistItem, idShoppinglist: number) : Promise<ResponseNewShoppinglistItem>{
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/createShoppinglistItemMetadata"
  const response = await axios.post(url, data);
  return response.data
}

async function InMemory() : Promise<ResponseNewShoppinglistItem> {
  return {
    idShoppinglistItemCreated: 1,
    created: true,
    shoppinglistItemCalculatedPrice: 1,
    totalPrice: 1,
    creationDate: "02/10/2025"
  }
}

async function createShoppinglistItem(data: ResquestNewShoppinglistItem, idShoppinglist: number) : Promise<ResponseNewShoppinglistItem> {
  return import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(data, idShoppinglist);
}

export { createShoppinglistItem };