import axios from "axios";
import { ResponseGetAllItemUnitFromShoppinglistItem } from "../models/responses/ResponseGetAllItemUnitFromShoppinglistItem";

/*async function InMemory() : Promise<ResponseGetAllItemUnitFromShoppinglistItem> {

}*/
async function Api(
  id: number
): Promise<ResponseGetAllItemUnitFromShoppinglistItem> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    id +
    "/itemsUnits";
  return (await axios.get(url)).data;
}

async function getAllItemUnitFromShoppinglistItem(
  id: number
): Promise<ResponseGetAllItemUnitFromShoppinglistItem> {
  return await Api(id);
}

export { getAllItemUnitFromShoppinglistItem };
