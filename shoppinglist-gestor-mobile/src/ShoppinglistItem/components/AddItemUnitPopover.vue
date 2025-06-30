<script setup lang="ts">
import {
  IonButton,
  IonContent,
  IonInput,
  IonItem,
  IonPopover,
} from "@ionic/vue";
import { ref } from "vue";
import {
  defaultRequestAddUnitaryPriceItemUnit,
  RequestAddUnitaryPriceItemUnit,
} from "@/ShoppinglistItem/infrastructure/models/RequestAddUnitaryPriceItemUnit";
import { useAddItemUnitToShoppinglistItem } from "../application/useAddItemUnitToShoppinglistItem";

const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

const form = ref<RequestAddUnitaryPriceItemUnit>({
  ...defaultRequestAddUnitaryPriceItemUnit,
});

const { refetch: addItemUnitToShoppinglistItem } =
  useAddItemUnitToShoppinglistItem();

function addItem() {
  if (params.idShoppinglistItem) {
    form.value.shoppinglistItemId = params.idShoppinglistItem;
    addItemUnitToShoppinglistItem(form.value);
  }
}
</script>
<template>
  <IonButton :id="'click-trigger' + params.idShoppinglistItem">Añadir unidad</IonButton>
  <IonPopover :trigger="'click-trigger' + params.idShoppinglistItem" trigger-action="click">
    <IonContent class="ion-padding">
      <IonItem>
        <IonInput
          v-model="form.quantity"
          label="Cantidad"
          type="number"
          placeholder="0"
        ></IonInput>
      </IonItem>
      <IonItem>
        <IonInput
          v-model="form.price"
          label="Precio"
          type="number"
          placeholder="0.0€"
        ></IonInput>
      </IonItem>
      <IonButton color="primary" @click="addItem">Añadir</IonButton>
    </IonContent>
  </IonPopover>
</template>
<style lang="css"></style>
