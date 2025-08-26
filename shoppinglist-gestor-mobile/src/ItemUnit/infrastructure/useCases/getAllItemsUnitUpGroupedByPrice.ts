import responseGettAllItemUnitUpGroupedByPrice from "@/ItemUnit/infrastructure/mocks/responseGetAllItemUnitUpGroupedByPrice.json";
import { createItemUnitUpGroupedByPriceList } from "../services/itemUnitService";
import axios from "axios";

async function InMemory(): Promise<ResponseGetAllItemUnitUpGroupedByPrice> {
  return responseGettAllItemUnitUpGroupedByPrice as ResponseGetAllItemUnitUpGroupedByPrice;
}

async function Api(
  idShoppinglistItem: number
): Promise<ResponseGetAllItemUnitUpGroupedByPrice> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    idShoppinglistItem +
    "/itemsUnitsUpGroupedByPrice";
  return (await axios.get(url)).data;
}

async function getAllItemsUnitUpGroupedByPrice(
  idShoppinglistitem: number
): Promise<ItemUnitUpGroupedByPriceList> {
  const response =
    import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory()
      : await Api(idShoppinglistitem);
  return createItemUnitUpGroupedByPriceList(response);
}

export { getAllItemsUnitUpGroupedByPrice };
