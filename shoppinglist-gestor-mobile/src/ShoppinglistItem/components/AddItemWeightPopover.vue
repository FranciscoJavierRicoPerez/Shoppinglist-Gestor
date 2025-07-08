<script setup lang="ts">
import {
  IonButton,
  IonContent,
  IonIcon,
  IonInput,
  IonItem,
  IonPopover,
} from "@ionic/vue";
import {
  defaultRequestAddWeightPriceItemUnit,
  RequestAddWeightPriceItemUnit,
} from "../infrastructure/models/RequestAddWeightPriceItemUnit";
import { ref } from "vue";
import { useAddItemUnitToShoppinglistItem } from "../application/useAddItemUnitToShoppinglistItem";
const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

const form = ref<RequestAddWeightPriceItemUnit>({
  ...defaultRequestAddWeightPriceItemUnit,
});

const { refetch: addItemUnitToShoppinglistItem } =
  useAddItemUnitToShoppinglistItem();

function addItem() {
  if (params.idShoppinglistItem) {
    form.value.shoppinglistItemId = params.idShoppinglistItem;
    addItemUnitToShoppinglistItem(null, form.value, false);
  }
}
</script>
<template>
  <IonButton
    size="small"
    shape="round"
    expand="block"
    :id="'click-trigger' + params.idShoppinglistItem"
  >
    <IonIcon name="add-circle-outline"></IonIcon> Kg/€
  </IonButton>
  <IonPopover
    :trigger="'click-trigger' + params.idShoppinglistItem"
    trigger-action="click"
  >
    <IonContent class="ion-padding">
      <IonItem>
        <IonInput
          v-model="form.weight"
          label="Peso en Kg"
          type="number"
          placeholder="0"
        ></IonInput>
      </IonItem>
      <IonItem>
        <IonInput
          v-model="form.priceKg"
          label="Precio KG/€"
          type="number"
          placeholder="0"
        ></IonInput>
      </IonItem>
      <IonButton color="primary" size="small" expand="block" @click="addItem"
        >Añadir</IonButton
      >
    </IonContent>
  </IonPopover>
</template>
<style lang="css"></style>
