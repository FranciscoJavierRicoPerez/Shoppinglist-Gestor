export function createItemUnitUpGroupedByPriceList(
  data: ResponseGetAllItemUnitUpGroupedByPrice
): ItemUnitUpGroupedByPriceList {
  return {
    totalPrice: data.totalPrice,
    itemsUpGrouped: createItemUnitGroupedList(data.itemsUpGrouped),
  };
}

export function createItemUnitGroupedList(
  data: ResponseItemUnitUpGrouped[]
): ItemUnitUpGrouped[] {
  let response: ItemUnitUpGrouped[] = [];
  data.forEach((element) => {
    response.push({
      quantity: element.quantity,
      price: element.price,
      calculatedPrice: element.calculatedPrice,
    });
  });
  return response;
}
