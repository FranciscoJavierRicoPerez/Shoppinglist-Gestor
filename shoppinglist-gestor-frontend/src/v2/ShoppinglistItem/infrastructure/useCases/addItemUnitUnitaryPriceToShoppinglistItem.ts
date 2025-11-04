import axios from "axios";
import { RequestAddUnitaryPriceItemUnit } from "../models/RequestAddUnitaryPriceItemUnit";

async function InMemory(data: RequestAddUnitaryPriceItemUnit| null) {}

async function Api(data: RequestAddUnitaryPriceItemUnit | null) {
  if (data) {
    const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    data.shoppinglistItemId +
    "/addItemUnitUP";
    await axios.post(url, {
      createItemUnit: true,
      createUpItemUnitData: {
        quantity: data.quantity,
        unitaryPrice: data.price
      },
      createWpItemUnitData: null
    })
  } 
}

async function addItemUnitUnitaryPriceToShoppinglistItem(
  data: RequestAddUnitaryPriceItemUnit |null
) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory(data)
    : await Api(data);
}

export { addItemUnitUnitaryPriceToShoppinglistItem };
