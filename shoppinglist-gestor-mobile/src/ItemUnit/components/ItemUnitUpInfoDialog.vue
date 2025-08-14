<script setup lang="ts">
import {
  IonButton,
  IonIcon,
  IonChip,
  IonModal,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonItem,
  IonInput,
} from "@ionic/vue";
import { useGetAllItemUnitUpGroupedByPrice } from "@/ItemUnit/application/useGetAllItemUnitUpGroupedByPrice";
import { onMounted, ref } from "vue";
import { useAddQuantityToItemUp } from "@/ItemUnit/application/useAddQuantityToItemUp";
import { useReduceQuantityToItemUp } from "@/ItemUnit/application/useReduceQuantityToItemUp";
import {
  defaultRequestAddUnitaryPriceItemUnit,
  RequestAddUnitaryPriceItemUnit,
} from "@/ShoppinglistItem/infrastructure/models/RequestAddUnitaryPriceItemUnit";
import { useAddItemUnitToShoppinglistItem } from "@/ShoppinglistItem/application/useAddItemUnitToShoppinglistItem";

const { refetch: getAllItemUnitUpGroupedByPrice } =
  useGetAllItemUnitUpGroupedByPrice();

const { refetch: addQuantityItemUp } = useAddQuantityToItemUp();
const { refetch: reduceQuantityItemUp } = useReduceQuantityToItemUp();

const itemsUnitsGrouped = ref<ItemUnitUpGroupedByPriceList>();

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

onMounted(async () => {
  itemsUnitsGrouped.value = await getAllItemUnitUpGroupedByPrice();
});

async function addItem() {
  if (params.idShoppinglistItem) {
    form.value.shoppinglistItemId = params.idShoppinglistItem;
    await addItemUnitToShoppinglistItem(form.value, null);
  }
}

async function addQuantity(idShoppinglistItem: number, itemPrice: number) {
  await addQuantityItemUp(idShoppinglistItem, itemPrice);
}

async function reduceQuantity(idShoppinglistItem: number, itemPrice: number) {
  await reduceQuantityItemUp(idShoppinglistItem, itemPrice);
}
</script>
<template>
  <IonButton :id="'open-modal' + params.idShoppinglistItem" expand="block">
    <IonIcon name="add-circle-outline"></IonIcon>Detalle
  </IonButton>
  <IonModal
    :trigger="'open-modal' + params.idShoppinglistItem"
    :initial-breakpoint="1"
    :breakpoints="[0, 1]"
  >
    <div class="block">
      <div id="addNewItemForm">
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Nuevo Item</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
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
          </IonCardContent>
        </IonCard>
      </div>

      <IonCard>
        <IonCardHeader>
          <IonCardTitle>Detalle</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
          <h1>
            <IonChip color="warning"
              >Coste total: {{ itemsUnitsGrouped?.totalPrice }} €</IonChip
            >
          </h1>
          <!-- TODO: TENGO QUE HACER QUE ESTO SEA UNA LISTA SCROLLEABLE -->
          <div v-for="itemUnitUp in itemsUnitsGrouped?.itemsUpGrouped">
            <IonCard style="background-color: rgb(237, 255, 224)">
              <IonCardHeader>
                <IonCardTitle
                  >Coste unitario por precio:{{
                    itemUnitUp.calculatedPrice
                  }}</IonCardTitle
                >
              </IonCardHeader>
              <IonCardContent>
                <IonChip color="primary"
                  >Cantidad: {{ itemUnitUp.quantity }} unidades</IonChip
                >
                <IonChip color="primary"
                  >Precio Unitario: {{ itemUnitUp.price }} €</IonChip
                >
              </IonCardContent>
              <div style="margin-left: 2.5rem; margin-bottom: 1rem">
                <IonButton @click="addQuantity">+ Cantidad</IonButton>
                <IonButton @click="reduceQuantity" color="danger"
                  >- Cantidad</IonButton
                >
              </div>
            </IonCard>
          </div>
        </IonCardContent>
      </IonCard>
    </div>
  </IonModal>
</template>
<style scoped lang="css">
.block {
  width: 100%;
  height: 800px;
  display: block;
  align-items: center;
  justify-content: center;
}
ion-modal {
  --height: auto;
}
</style>
