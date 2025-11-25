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
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
});

const shoppinglistItem = ref<ShoppinglistItemMetadata>();

const store = useShoppinglistDetailsViewStore();

const emit = defineEmits(["update-visible-elements"]);

onMounted(() => {
  shoppinglistItem.value = props.shoppinglistItem;
  store.setShoppinglistItemMetadata(shoppinglistItem.value);
});

async function removeShoppinglistItem(idItem: number) {
  let response = await deleteShoppinglistItem(idItem);
  if (response.delete) {
    await updateShoppinglistTotalPrice(Number(route.params.id));
    store.removeShoppinglistItemMetadata(idItem);
    store.setTotalPrice(store.shoppinglistDetailsViewItems);
    emit("update-visible-elements");
  }
}

function getCalculteSystemCode(data: any): string {
  return data;
}
</script>
<template>
  <div v-if="shoppinglistItem">
    <IonItem>
      <IonCard
        :class="[
          {
            'customCard-UP-background':
              getCalculteSystemCode(shoppinglistItem.calculateSystemCode) ===
              'UP',
          },
          {
            'customCard-WP-background':
              getCalculteSystemCode(shoppinglistItem.calculateSystemCode) ===
              'WP',
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
    </IonItem>
  </div>
</template>
<style lang="css">
.customCard-UP-background {
  background-color: rgb(241, 248, 141);
}

.customCard-WP-background {
  background-color: rgb(189, 249, 148);
}
</style>
