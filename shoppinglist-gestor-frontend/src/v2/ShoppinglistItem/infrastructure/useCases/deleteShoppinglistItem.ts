import axios from "axios";
import { ResponseDeleteShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/ResponseDeleteShoppinglistItem";

async function Api(idItem: number): Promise<ResponseDeleteShoppinglistItem> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    idItem +
    "/delete";
  const response = await axios.delete(url);
  return response.data;
}

async function InMemory(
  idItem: number
): Promise<ResponseDeleteShoppinglistItem> {
  return {
    message: "",
    delete: true,
  };
}

async function deleteShoppinglistItem(
  idItem: number
): Promise<ResponseDeleteShoppinglistItem> {
  let result =
    import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory(idItem)
      : await Api(idItem);
  return result;
}

export { deleteShoppinglistItem };
