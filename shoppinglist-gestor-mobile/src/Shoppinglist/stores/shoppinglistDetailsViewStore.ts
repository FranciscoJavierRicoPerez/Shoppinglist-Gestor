import { defineStore } from "pinia";
import { ref } from "vue";
import { ShoppinglistViewDetails } from "../domain/ShoppinglistViewDetails";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { c } from "vitest/dist/reporters-5f784f42";

export const useShoppinglistDetailsViewStore = defineStore(
  "shoppinglistDetailsViewStore",
  () => {
    const shoppinglistDetailsView = ref<ShoppinglistViewDetails>();
    const shoppinglistDetailsViewItems = ref<ShoppinglistItemMetadata[]>([]);
    const totalPrice = ref<number>(-1);

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

    return {
      shoppinglistDetailsView,
      shoppinglistDetailsViewItems,
      totalPrice,
      setShoppinglistDetailsView,
      setShoppinglistDetailsViewItems,
      removeShoppinglistItemMetadata,
      setTotalPrice
    };
  }
);
