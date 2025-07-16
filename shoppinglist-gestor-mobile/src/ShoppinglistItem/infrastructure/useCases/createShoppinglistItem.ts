import { ResquestNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";
import axios from "axios";
import { ResponseNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/ResponseNewShoppinglistItem";

async function Api(data: ResquestNewShoppinglistItem) : Promise<ResponseNewShoppinglistItem>{
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    data.shoppinglistId +
    "/createItem";
  const response = await axios.post(url, {
    productId: data.requestProduct.productId,
    productName: data.requestProduct.name,
    calculateSystemCode: data.calculateSystem,
    unitaryPrice: data.unitaryPrice,
    quantity: data.quantity
  });
  return response.data
}

async function InMemory() : Promise<ResponseNewShoppinglistItem> {
  return {
    idItemCreated: 1,
    created: true,
    responseMessage: 'CREADO'
  }
}

async function createShoppinglistItem(data: ResquestNewShoppinglistItem) : Promise<ResponseNewShoppinglistItem> {
  return import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(data);
}

export { createShoppinglistItem };