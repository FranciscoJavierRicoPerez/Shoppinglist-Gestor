import responseGetShoppinglistDetailsMetadata from "@/Shoppinglist/infrastructure/mocks/responseGetShoppinglistDetailsMetadata.json";
import { ResponseGetShoppinglistDetailsMetadata } from "../models/ResponseGetShoppinglistDetailsMetadata";
import axios from "axios";
import { createShoppinglistDetailsViewMetadata } from "../services/ShoppinglistService";
import { ShoppinglistViewDetails } from "@/Shoppinglist/domain/ShoppinglistViewDetails";

async function Api(
  idShoppinglist: number
): Promise<ResponseGetShoppinglistDetailsMetadata> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistview/v1/" +
    idShoppinglist +
    "/details";
  const response = await axios.get(url);
  return response.data;
}

async function InMemory(): Promise<ResponseGetShoppinglistDetailsMetadata> {
  return responseGetShoppinglistDetailsMetadata as ResponseGetShoppinglistDetailsMetadata;
}

async function getShoppinglistDetailsMetadata(
  idShoppinglist: number
): Promise<ShoppinglistViewDetails> {
  const response: ResponseGetShoppinglistDetailsMetadata =
    import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory()
      : await Api(idShoppinglist);
  return createShoppinglistDetailsViewMetadata(response);
}

export { getShoppinglistDetailsMetadata };
