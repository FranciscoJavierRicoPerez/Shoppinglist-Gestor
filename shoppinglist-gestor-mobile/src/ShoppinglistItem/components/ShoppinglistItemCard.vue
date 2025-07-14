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
  IonIcon,
} from "@ionic/vue";

import { onMounted, ref, type PropType } from "vue";
import { RouterLink } from "vue-router";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { useDeleteShoppinglistItem } from "../application/useDeleteShoppinglistItem";
import AddItemUnitPopover from "./AddItemUnitPopover.vue";
import RemoveItemUnitPopover from "./RemoveItemUnitPopover.vue";
import AddItemWeightPopover from "./AddItemWeightPopover.vue";
import { useShoppinglistItemStore } from "../stores/shoppinglistItemStore";

const { refetch: deleteShoppinglistItem } = useDeleteShoppinglistItem();

const props = defineProps({
  shoppinglistItemList: {
    type: Object as PropType<ShoppinglistItemMetadata[]>,
    default: () => null,
  },
});

const list = ref<ShoppinglistItemMetadata[]>();

const store = useShoppinglistItemStore();

onMounted(() => {
  store.setShoppinglistMetadataArray(props.shoppinglistItemList);
  list.value = store.shoppinglistItemMetadataArray;
});

async function removeShoppinglistItem(idItem: number) {
  let response = await deleteShoppinglistItem(idItem);
  if (response.delete) {
    store.removeShoppinglistItemMetadata(idItem);
    list.value = store.shoppinglistItemMetadataArray;
  }
}

function getCalculteSystemCode(data: ShoppinglistItemMetadata | any): string {
  return data.calculateSystemCode;
}
</script>
<template>
  <IonItem v-for="shoppinglistItem in list">
    <IonLabel>
      <IonCard
        :class="[
          {
            'customCard-UP-background':
              getCalculteSystemCode(shoppinglistItem) === 'UP',
          },
          {
            'customCard-WP-background':
              getCalculteSystemCode(shoppinglistItem) === 'WP',
          },
        ]"
      >
        <IonCardHeader>
          <IonCardTitle>{{ shoppinglistItem.name }}</IonCardTitle>
        </IonCardHeader>
        <IonCardSubtitle style="margin-left: 1rem">
          <IonChip color="primary"
            >Añadido el: {{ shoppinglistItem.assignationToListDate }}</IonChip
          >
          <IonChip color="success"
            >Sistema de calculo:
            {{ shoppinglistItem.calculateSystemCode }}</IonChip
          >
          <IonChip color="tertiary"> Precio: {{ shoppinglistItem.calculatedPrice }} </IonChip>
        </IonCardSubtitle>
        <IonCardContent>
          <!-- <RouterLink :to="`/product/${shoppinglistItem.id}}`"
            ><IonButton shape="round" color="tertiary"
              >Ver</IonButton
            ></RouterLink
          > -->
          <div v-if="shoppinglistItem.calculateSystemCode === 'UP'">
            <AddItemUnitPopover
              :idShoppinglistItem="shoppinglistItem.id"
            ></AddItemUnitPopover>
            <RemoveItemUnitPopover
              :idShoppinglistItem="shoppinglistItem.id"
            ></RemoveItemUnitPopover>
          </div>
          <div v-else>
            <AddItemWeightPopover
              :idShoppinglistItem="shoppinglistItem.id"
            ></AddItemWeightPopover>
          </div>
          <IonButton
            shape="round"
            color="danger"
            size="small"
            expand="block"
            @click="removeShoppinglistItem(shoppinglistItem.id)"
            ><IonIcon name="trash-outline"></IonIcon
          ></IonButton>
        </IonCardContent>
      </IonCard>
    </IonLabel>
  </IonItem>
</template>
<style lang="css">
.customCard-UP-background {
  background-color: rgb(250, 252, 224);
}

.customCard-WP-background {
  background-color: rgb(252, 246, 224);
}
</style>
