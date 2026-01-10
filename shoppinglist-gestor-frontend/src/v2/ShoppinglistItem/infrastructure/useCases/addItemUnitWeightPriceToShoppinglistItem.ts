import axios from "axios";
import { RequestAddWeightPriceItemUnit } from "../models/RequestAddWeightPriceItemUnit";

async function InMemory(data: RequestAddWeightPriceItemUnit | null) {}

async function Api(data: RequestAddWeightPriceItemUnit | null) {
  if (data) {
    const url =
      import.meta.env.VITE_API_URL_COMPUTER +
      "api/shoppinglistitem/v1/" +
      data.shoppinglistItemId +
      "/addItemUnitWP";
    await axios.post(url, data);
  }
}

async function addItemUnitWeightPriceToShoppinglistItem(
  data: RequestAddWeightPriceItemUnit | null
) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory(data)
    : await Api(data);
}

export { addItemUnitWeightPriceToShoppinglistItem };
