import axios from "axios";

async function InMemory(): Promise<void> {}

async function Api(
  idItemUnit: number,
  idShoppinglistItem: number
): Promise<void> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    idShoppinglistItem +
    "/removeItemUnit/" +
    idItemUnit;
  await axios.delete(url);
}

async function removeItem(
  idItemUnit: number,
  idShoppinglistItem: number
): Promise<void> {
  return await Api(idItemUnit, idShoppinglistItem);
}

export { removeItem };
