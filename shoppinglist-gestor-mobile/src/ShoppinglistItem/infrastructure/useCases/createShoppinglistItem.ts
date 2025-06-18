import { ResquestNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";
import axios from "axios";

async function Api(data: ResquestNewShoppinglistItem) {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    data.shoppinglistId +
    "/createItem";
  const response = await axios.post(url, {
    productId: data.requestProduct.productId,
    productName: data.requestProduct.name,
    calculateSystemCode: data.calculateSystem,
  });
}

async function InMemory() {}

async function createShoppinglistItem(data: ResquestNewShoppinglistItem) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(data);
}

export { createShoppinglistItem };