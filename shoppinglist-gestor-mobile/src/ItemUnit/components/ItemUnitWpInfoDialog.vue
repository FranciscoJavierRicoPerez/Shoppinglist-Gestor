<script setup lang="ts">
import { useAddItemUnitToShoppinglistItem } from "@/ShoppinglistItem/application/useAddItemUnitToShoppinglistItem";
import {
  defaultRequestAddWeightPriceItemUnit,
  RequestAddWeightPriceItemUnit,
} from "@/ShoppinglistItem/infrastructure/models/RequestAddWeightPriceItemUnit";
import {
  IonButton,
  IonModal,
  IonItem,
  IonInput,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonChip,
} from "@ionic/vue";
import { onMounted, ref, watch } from "vue";
import { useGetItemWpDetails } from "../application/useGetItemWpDetails";
import { useRoute } from "vue-router";
import { useUpdateShoppinglistTotalPrice } from "@/Shoppinglist/application/useUpdateShoppinglistTotalPrice";

const { refetch: updateShoppinglistTotalPrice } =
  useUpdateShoppinglistTotalPrice();

const route = useRoute();

const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

const itemWpMetadata = ref<ResponseItemUnitWpMetadata>();

const form = ref<RequestAddWeightPriceItemUnit>({
  ...defaultRequestAddWeightPriceItemUnit,
});

const { refetch: addItemUnitToShoppinglistItem } =
  useAddItemUnitToShoppinglistItem();

const { refetch: getItemWpDetails } = useGetItemWpDetails();

const weightResume = ref<number>(0);
const priceKgResume = ref<number>(0);
const calculatedPriceResume = ref<number>(0);

onMounted(async () => {
  if (params.idShoppinglistItem) {
    itemWpMetadata.value = await getItemWpDetails(params.idShoppinglistItem);
    weightResume.value = itemWpMetadata.value.weight;
    priceKgResume.value = itemWpMetadata.value.priceKg;
    calculatedPriceResume.value = itemWpMetadata.value.calculatedPrice;
  }
});

watch(form.value, (newForm) => {
  weightResume.value = newForm.weight;
  priceKgResume.value = newForm.priceKg;
  calculatedPriceResume.value = weightResume.value * priceKgResume.value;
});

async function addItem() {
  if (params.idShoppinglistItem) {
    form.value.shoppinglistItemId = params.idShoppinglistItem;
    await addItemUnitToShoppinglistItem(null, form.value, false);
    await updateShoppinglistTotalPrice(Number(route.params.id));
  }
}
</script>
<template>
  <IonButton :id="'open-modal' + params.idShoppinglistItem" expand="block">
    <IonIcon name="add-circle-outline"></IonIcon> Kg/€
  </IonButton>
  <IonModal
    :trigger="'open-modal' + params.idShoppinglistItem"
    :initial-breakpoint="1"
    :breakpoints="[0, 1]"
  >
    <div class="block">
      <div id="addNewItemWpForm">
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Nuevo Item Unit Wp</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
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
            <IonButton
              color="primary"
              size="small"
              expand="block"
              @click="addItem"
              >Añadir</IonButton
            >
          </IonCardContent>
        </IonCard>
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Detalle</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <IonChip>Peso: {{ weightResume }} Kg</IonChip>
            <IonChip>Precio: {{ priceKgResume }} Kg/€</IonChip>
            <IonChip
              >Precio calculado: {{ calculatedPriceResume }}</IonChip
            >
          </IonCardContent>
        </IonCard>
      </div>
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
