import axios from "axios";

async function Api(idShoppinglist: number, idShoppinglistItem: number) {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglist/v1/" +
    idShoppinglist +
    "/addShoppinglistItem";
  await axios.post(url, null, {
    params: {
      idShoppinglistItem: idShoppinglistItem,
    },
  });
}
async function InMemory() {}
async function addShoppinglistItemToShoppinglist(
  idShoppinglist: number,
  idShoppinglistItem: number
) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
    ? await InMemory()
    : await Api(idShoppinglist, idShoppinglistItem);
}
export { addShoppinglistItemToShoppinglist };
