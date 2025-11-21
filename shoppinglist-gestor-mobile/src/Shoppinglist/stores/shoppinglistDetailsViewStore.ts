import { defineStore } from "pinia";
import { ref } from "vue";
import { ShoppinglistViewDetails } from "../domain/ShoppinglistViewDetails";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { ShoppinglistMetadata } from "../domain/ShoppinglistMetadata";

export const useShoppinglistDetailsViewStore = defineStore(
  "shoppinglistDetailsViewStore",
  () => {
    const shoppinglistDetailsView = ref<ShoppinglistViewDetails>();
    const shoppinglistDetailsViewItems = ref<ShoppinglistItemMetadata[]>([]);
    const totalPrice = ref<number>(-1);
    const shoppinglistItemMetadata = ref<ShoppinglistItemMetadata>();
    const shoppinglistMetadata = ref<ShoppinglistMetadata>();

    function setShoppinglistMetadata(data: ShoppinglistMetadata) {
      shoppinglistMetadata.value = data
    }

    function setShoppinglistDetailsView(data: ShoppinglistViewDetails) {
      shoppinglistDetailsView.value = data;
    }

    function setShoppinglistDetailsViewItems(data: ShoppinglistItemMetadata[]) {
      shoppinglistDetailsViewItems.value = data;
    }

    function setTotalPrice(data: ShoppinglistItemMetadata[]) {
      let totalPriceCalculated = 0;
      data.map(
        (element) =>
          (totalPriceCalculated +=
            element.calculatedPrice !== null ? element.calculatedPrice : 0)
      );
      totalPrice.value = totalPriceCalculated;
    }

    function removeShoppinglistItemMetadata(id: number) {
      setShoppinglistDetailsViewItems(
        shoppinglistDetailsViewItems.value.filter(
          (element) => element.id !== id
        )
      );
    }

    function updateTotalPriceWithWPItemValue(
      oldItemWpCalculatedPrice: number | undefined,
      newItemWpCalculatedPrice: number
    ) {
      if (oldItemWpCalculatedPrice) {
        totalPrice.value = totalPrice.value - oldItemWpCalculatedPrice;
        totalPrice.value = totalPrice.value + newItemWpCalculatedPrice;
      }
    }

    function updateTotalPriceWithUpItemValue(
      oldItemUpCalculatedPrice: number | undefined,
      newItemUpCalculatedPrice: number
    ) {
      if (oldItemUpCalculatedPrice) {
        totalPrice.value = totalPrice.value - oldItemUpCalculatedPrice;
        totalPrice.value = totalPrice.value + newItemUpCalculatedPrice;
      }
    }

    function addShoppinglistItemMetadata(data: ShoppinglistItemMetadata) {
      shoppinglistDetailsViewItems.value.push(data);
    }

    function getShoppinglistItem(id: number) {
      shoppinglistItemMetadata.value = shoppinglistDetailsViewItems.value.filter(element => element.id != id)[0]
    }

    function setShoppinglistItemMetadata(data : ShoppinglistItemMetadata) {
      shoppinglistItemMetadata.value = data
    }

    return {
      shoppinglistDetailsView,
      shoppinglistDetailsViewItems,
      totalPrice,
      setShoppinglistDetailsView,
      setShoppinglistDetailsViewItems,
      removeShoppinglistItemMetadata,
      setTotalPrice,
      addShoppinglistItemMetadata,
      updateTotalPriceWithWPItemValue,
      updateTotalPriceWithUpItemValue,
      getShoppinglistItem,
      shoppinglistItemMetadata,
      setShoppinglistItemMetadata,
      shoppinglistMetadata,
      setShoppinglistMetadata
    };
  }
);
