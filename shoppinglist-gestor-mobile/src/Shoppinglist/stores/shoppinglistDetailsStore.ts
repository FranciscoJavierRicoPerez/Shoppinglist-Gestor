import { defineStore } from "pinia";
import { ShoppinglistDetails } from "@/Shoppinglist/domain/ShoppinglistDetails";
import { ref } from "vue";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";

export const useShoppinglistDetailsStore = defineStore(
  "shoppinglistDetailsStore",
  () => {
    const shoppinglistDetails = ref<ShoppinglistDetails>();
    const totalPrice = ref<number>(0);

    function setShoppinglistDetails(data: ShoppinglistDetails) {
      shoppinglistDetails.value = data;
      totalPrice.value = shoppinglistDetails.value.totalPrice;
    }

    function updateTotalPrice() {
      let newTotalPrice: number = 0;
      shoppinglistDetails.value?.items.forEach((element) => {
        if (element !== undefined) {
          if (element.calculatedPrice !== null) {
            newTotalPrice += element.calculatedPrice;
          }
        }
      });
      totalPrice.value = newTotalPrice;
    }

    function addItem(data: ShoppinglistItemMetadata) {
      shoppinglistDetails.value?.items.push(data);
    }

    return {
      shoppinglistDetails,
      totalPrice,
      setShoppinglistDetails,
      updateTotalPrice,
      addItem,
    };
  }
);
