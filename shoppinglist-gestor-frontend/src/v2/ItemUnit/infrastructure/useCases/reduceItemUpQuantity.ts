import axios from "axios";

async function InMemory(): Promise<void> {
  console.log("InMemory: Reduciendo la cantidad de unidades");
}

async function Api(
  idShoppinglistItem: number,
  itemPrice: number
): Promise<void> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER + "api/itemunit/reduceQuantity";
  return await axios.post(url, {
    idShoppinglistItem: idShoppinglistItem,
    itemPrice: itemPrice,
  });
}

async function reduceItemUpQuantity(
  idShoppinglistItem: number,
  itemPrice: number
): Promise<void> {
  return import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(idShoppinglistItem, itemPrice);
}

export { reduceItemUpQuantity };
