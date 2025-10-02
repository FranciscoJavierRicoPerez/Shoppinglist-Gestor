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
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { useDeleteShoppinglistItem } from "@/ShoppinglistItem/application/useDeleteShoppinglistItem";
import { useShoppinglistItemStore } from "@/ShoppinglistItem/stores/shoppinglistItemStore";
import ItemUnitUpInfoDialog from "@/ItemUnit/components/ItemUnitUpInfoDialog.vue";
import ItemUnitWpInfoDialog from "@/ItemUnit/components/ItemUnitWpInfoDialog.vue";
import { useUpdateShoppinglistTotalPrice } from "@/Shoppinglist/application/useUpdateShoppinglistTotalPrice";
import { useRoute } from "vue-router";
import { useShoppinglistDetailsViewStore } from "@/Shoppinglist/stores/shoppinglistDetailsViewStore";

const { refetch: deleteShoppinglistItem } = useDeleteShoppinglistItem();
const { refetch: updateShoppinglistTotalPrice } =
  useUpdateShoppinglistTotalPrice();

const route = useRoute();

const props = defineProps({
  shoppinglistItemList: {
    type: Object as PropType<ShoppinglistItemMetadata[]>,
    default: () => null,
  },
});

const list = ref<ShoppinglistItemMetadata[]>();

// const store = useShoppinglistItemStore();
const store = useShoppinglistDetailsViewStore();

onMounted(() => {
  // store.setShoppinglistMetadataArray(props.shoppinglistItemList);
  list.value = store.shoppinglistDetailsViewItems;
  console.log(list.value)
});

async function removeShoppinglistItem(idItem: number) {
  let response = await deleteShoppinglistItem(idItem);
  if (response.delete) {
    await updateShoppinglistTotalPrice(Number(route.params.id));
    store.removeShoppinglistItemMetadata(idItem);
    store.setTotalPrice(store.shoppinglistDetailsViewItems)
    list.value = store.shoppinglistDetailsViewItems;
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
          <IonChip color="tertiary">
            Precio: {{ shoppinglistItem.calculatedPrice }}
          </IonChip>
        </IonCardSubtitle>
        <IonCardContent>
          <div v-if="shoppinglistItem.calculateSystemCode === 'UP'">
            <ItemUnitUpInfoDialog
              :idShoppinglistItem="shoppinglistItem.id"
            ></ItemUnitUpInfoDialog>
          </div>
          <div v-else>
            <ItemUnitWpInfoDialog
              :idShoppinglistItem="shoppinglistItem.id"
            ></ItemUnitWpInfoDialog>
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
