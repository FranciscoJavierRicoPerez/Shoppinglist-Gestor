<script setup lang="ts">
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonChip,
  IonItem,
  IonLabel,
} from "@ionic/vue";

import type { PropType } from "vue";
import { RouterLink } from "vue-router";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { useDeleteShoppinglistItem } from "../application/useDeleteShoppinglistItem";
import AddItemUnitPopover from "./AddItemUnitPopover.vue";
import RemoveItemUnitPopover from "./RemoveItemUnitPopover.vue";

const { refetch: deleteShoppinglistItem } = useDeleteShoppinglistItem();

const props = defineProps({
  shoppinglistItemList: {
    type: Object as PropType<ShoppinglistItemMetadata[]>,
    default: () => null,
  },
});

async function removeShoppinglistItem(idItem: number) {
  let response = await deleteShoppinglistItem(idItem);
}

</script>
<template>
  <IonItem v-for="shoppinglistItem in shoppinglistItemList">
    <IonLabel>
      <IonCard class="customCard">
        <IonCardHeader>
          <IonCardTitle>{{ shoppinglistItem.name }}</IonCardTitle>
        </IonCardHeader>
        <IonCardSubtitle style="margin-left: 3rem">
          <IonChip color="primary"
            >Cantidad: {{ shoppinglistItem.assignationToListDate }}</IonChip
          >
          <IonChip color="success"
            >Precio: {{ shoppinglistItem.calculateSystemCode }}</IonChip
          >
        </IonCardSubtitle>
        <IonCardContent style="margin-left: 3rem">
          <RouterLink :to="`/product/${shoppinglistItem.id}}`"
            ><IonButton shape="round" color="tertiary"
              >Ver</IonButton
            ></RouterLink
          >
          <IonButton shape="round" color="danger" @click="deleteShoppinglistItem(shoppinglistItem.id)">Eliminar</IonButton>
          <AddItemUnitPopover :idShoppinglistItem="shoppinglistItem.id"></AddItemUnitPopover>
          <RemoveItemUnitPopover :idShoppinglistItem="shoppinglistItem.id"></RemoveItemUnitPopover>
        </IonCardContent>
      </IonCard>
    </IonLabel>
  </IonItem>
</template>
<style lang="css">
.customCard {
  background-color: rgb(234, 250, 227)
}
</style>
