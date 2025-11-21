import axios from "axios";

async function Api(idShoppinglist: number): Promise<void> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglist/v1/" +
    idShoppinglist +
    "/updateTotalPrice";
  await axios.put(url);
}
async function InMemory(): Promise<void> {}
async function updateShoppinglistTotalPrice(
  idShoppinglist: number
): Promise<void> {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(idShoppinglist);
}

export { updateShoppinglistTotalPrice };
