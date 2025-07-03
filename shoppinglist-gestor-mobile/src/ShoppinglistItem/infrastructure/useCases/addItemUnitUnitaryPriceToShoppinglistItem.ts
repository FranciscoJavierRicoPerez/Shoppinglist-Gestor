import axios from "axios";
import { RequestAddUnitaryPriceItemUnit } from "../models/RequestAddUnitaryPriceItemUnit";

async function InMemory(data: RequestAddUnitaryPriceItemUnit) {}

async function Api(data: RequestAddUnitaryPriceItemUnit) {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    data.shoppinglistItemId +
    "/addItemUnit";
    await axios.post(url, data)
}

async function addItemUnitUnitaryPriceToShoppinglistItem(
  data: RequestAddUnitaryPriceItemUnit
) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory(data)
    : await Api(data);
}

export { addItemUnitUnitaryPriceToShoppinglistItem };
