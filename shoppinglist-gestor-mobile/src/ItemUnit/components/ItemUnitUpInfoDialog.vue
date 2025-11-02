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
  InfiniteScrollCustomEvent,
  IonContent,
  IonList,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
} from "@ionic/vue";
import { useGetAllItemUnitUpGroupedByPrice } from "@/ItemUnit/application/useGetAllItemUnitUpGroupedByPrice";
import { onMounted, ref } from "vue";
import {
  defaultRequestAddUnitaryPriceItemUnit,
  RequestAddUnitaryPriceItemUnit,
} from "@/ShoppinglistItem/infrastructure/models/RequestAddUnitaryPriceItemUnit";
import { useAddItemUnitToShoppinglistItem } from "@/ShoppinglistItem/application/useAddItemUnitToShoppinglistItem";
import { useRoute } from "vue-router";
import { useUpdateShoppinglistTotalPrice } from "@/Shoppinglist/application/useUpdateShoppinglistTotalPrice";
import { useShoppinglistDetailsViewStore } from "@/Shoppinglist/stores/shoppinglistDetailsViewStore";

const { refetch: getAllItemUnitUpGroupedByPrice } =
  useGetAllItemUnitUpGroupedByPrice();

const { refetch: updateShoppinglistTotalPrice } =
  useUpdateShoppinglistTotalPrice();

const route = useRoute();

const itemsUnitsGrouped = ref<ItemUnitUpGroupedByPriceList>();

const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

const store = useShoppinglistDetailsViewStore();

const form = ref<RequestAddUnitaryPriceItemUnit>({
  ...defaultRequestAddUnitaryPriceItemUnit,
});

const { refetch: addItemUnitToShoppinglistItem } =
  useAddItemUnitToShoppinglistItem();

const actualElementsVisible = ref<ItemUnitUpGrouped[]>([]);

const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  generateItems();
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

onMounted(async () => {
  if (params.idShoppinglistItem) {
    itemsUnitsGrouped.value = await getAllItemUnitUpGroupedByPrice(
      params.idShoppinglistItem
    );
  }
  generateItems();
});

function generateItems() {
  if (itemsUnitsGrouped.value?.itemsUpGrouped !== undefined) {
    const start = actualElementsVisible.value.length;
    const aux_array: ItemUnitUpGrouped[] =
      itemsUnitsGrouped.value.itemsUpGrouped;
    for (let i = 0; i < 50; i++) {
      let element = aux_array[start + i];
      if (element !== undefined) {
        actualElementsVisible.value.push(aux_array[start + i]);
      }
    }
  }
}

async function addItem() {
  if (params.idShoppinglistItem) {
    form.value.shoppinglistItemId = params.idShoppinglistItem;
    store.updateTotalPriceWithUpItemValue(
      itemsUnitsGrouped.value?.totalPrice,
      form.value.price * form.value.quantity
    );
    await addItemUnitToShoppinglistItem(form.value, null);
    await updateShoppinglistTotalPrice(Number(route.params.id));
  }
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
      <IonContent>
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Detalle</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <!-- TODO: EXTRAER A UN COMPONENTE INDEPENDIENTE -->
            <h1>
              <IonChip color="danger">
                Precio total de la lista: {{ store.totalPrice }}
              </IonChip>
              <IonChip color="warning"
                >Precio total del item:
                {{ itemsUnitsGrouped?.totalPrice }} €</IonChip
              >
            </h1>
            <IonList>
              <IonItem v-for="itemUnitUp in actualElementsVisible">
                <IonCard style="background-color: rgb(237, 255, 224)">
                  <IonCardHeader>
                    <IonCardTitle
                      >Precio total de la unidad :
                      {{ itemUnitUp.calculatedPrice }} €</IonCardTitle
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
                  <!-- <div style="margin-left: 2.5rem; margin-bottom: 1rem">
                    <IonButton @click="addQuantity">+ Cantidad</IonButton>
                    <IonButton @click="reduceQuantity" color="danger"
                      >- Cantidad</IonButton
                    >
                  </div> -->
                </IonCard>
              </IonItem>
            </IonList>
            <IonInfiniteScroll @ion-infinite="ionInfinite">
              <IonInfiniteScrollContent></IonInfiniteScrollContent>
            </IonInfiniteScroll>
          </IonCardContent>
        </IonCard>
      </IonContent>
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
