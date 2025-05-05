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
import type { ShoppinglistItem } from "@/ShoppinglistItem/domain/ShoppinglistItem";
const props = defineProps({
  shoppinglistItemList: {
    type: Object as PropType<ShoppinglistItem[]>,
    default: () => null,
  },
});
</script>
<template>
  <IonItem v-for="shoppinglistItem in shoppinglistItemList">
    <IonLabel>
      <IonCard class="customCard">
        <IonCardHeader>
          <IonCardTitle>{{ shoppinglistItem.product?.name }}</IonCardTitle>
        </IonCardHeader>
        <IonCardSubtitle style="margin-left: 3rem">
          <IonChip color="primary"
            >Cantidad: {{ shoppinglistItem.quantity }}</IonChip
          >
          <IonChip color="success"
            >Precio: {{ shoppinglistItem.calculatedPrice }}</IonChip
          >
        </IonCardSubtitle>
        <IonCardContent style="margin-left: 3rem">
          <RouterLink :to="`/product/${shoppinglistItem.product?.id}}`"
            ><IonButton shape="round" color="tertiary"
              >Ver</IonButton
            ></RouterLink
          >
          <IonButton shape="round" color="danger">Eliminar</IonButton>
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
