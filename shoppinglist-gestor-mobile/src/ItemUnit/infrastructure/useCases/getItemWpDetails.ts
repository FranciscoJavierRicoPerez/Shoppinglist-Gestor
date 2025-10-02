import axios from "axios";

async function Api(
  idShoppinglistItem: number
): Promise<ResponseItemUnitWpMetadata> {
  const url =
    import.meta.env.VITE_API_URL_COMPUTER +
    "api/shoppinglistitem/v1/" +
    idShoppinglistItem +
    "/itemsUnitsWpInfo";
  return (await axios.get(url)).data;
}

async function InMemory() {}

async function getItemWpDetails(idShoppinglistItem: number) {
  import.meta.env.VITE_DATA_ACCESS === "LOCAL"
      ? await InMemory()
      : await Api(idShoppinglistItem);
}

export { getItemWpDetails };
