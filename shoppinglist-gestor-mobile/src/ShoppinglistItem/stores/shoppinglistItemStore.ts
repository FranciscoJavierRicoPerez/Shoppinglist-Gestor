import { defineStore } from "pinia";
import { ref } from "vue";
import { ShoppinglistItem } from "@/ShoppinglistItem/domain/ShoppinglistItem";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";

export const useShoppinglistItemStore = defineStore(
  "shoppinglistItemStore",
  () => {
    const shoppinglistItemArray = ref<ShoppinglistItem[]>([]);
    const shoppinglistItemMetadataArray = ref<ShoppinglistItemMetadata[]>([]);

    function setShoppinglistMetadataArray(data: ShoppinglistItemMetadata[]) {
      shoppinglistItemMetadataArray.value = data;
    }

    function removeShoppinglistItemMetadata(id: number) {
      debugger;
      setShoppinglistMetadataArray(
        shoppinglistItemMetadataArray.value.filter(
          (element) => element.id !== id
        )
      );
    }

    function addShoppinglistItemMetadata(data: ShoppinglistItemMetadata) {
        shoppinglistItemMetadataArray.value.push(data);
    }

    return {
      shoppinglistItemMetadataArray,
      setShoppinglistMetadataArray,
      removeShoppinglistItemMetadata,
      addShoppinglistItemMetadata
    };
  }
);
