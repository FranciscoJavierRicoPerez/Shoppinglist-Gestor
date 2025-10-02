<script setup lang="ts">
import {
  IonButton,
  IonButtons,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonContent,
  IonHeader,
  IonInput,
  IonModal,
  IonTitle,
  IonToolbar,
  IonItem,
  IonList,
  IonSelect,
  IonSelectOption,
  IonChip,
} from "@ionic/vue";
import { onMounted, ref, watch } from "vue";
import {
  defaultRequestNewShoppinglistItem,
  ResquestNewShoppinglistItem,
} from "@/ShoppinglistItem/infrastructure/models/RequestNewShoppinglistItem";
import { Product } from "@/Product/domain/Product";
import { useGetAllProducts } from "@/Product/application/useGetAllProducts";
import { useCreateShoppinglistItem } from "@/ShoppinglistItem/application/useCreateShoppinglistItem";
import { useRoute } from "vue-router";
import { useShoppinglistItemStore } from "@/ShoppinglistItem/stores/shoppinglistItemStore";
import { ResponseNewShoppinglistItem } from "@/ShoppinglistItem/infrastructure/models/ResponseNewShoppinglistItem";
import { useGetAllCalculateSystems } from "@/CalculateSystem/application/useGetAllCalculateSystems";
import {
  CalculateSystem,
  defaultCalculateSystem,
} from "@/CalculateSystem/domain/CalculateSystem";
import { useAddShoppinglistItemToShoppinglist } from "@/Shoppinglist/application/useAddShoppinglistItemToShoppinglist";
import { useUpdateShoppinglistTotalPrice } from "@/Shoppinglist/application/useUpdateShoppinglistTotalPrice";
import { useShoppinglistDetailsViewStore } from "@/Shoppinglist/stores/shoppinglistDetailsViewStore";
const route = useRoute();
const { refetch: getAllProductList } = useGetAllProducts();
const { refetch: createShoppinglistItem } = useCreateShoppinglistItem();
const { refetch: addShoppinglistItemToShoppinglist } =
  useAddShoppinglistItemToShoppinglist();
const { refetch: updateShoppinglistTotalPrice } =
  useUpdateShoppinglistTotalPrice();
const props = defineProps({
  openModal: {
    type: Boolean,
  },
});

const { refetch: getAllCalculateSystems } = useGetAllCalculateSystems();

// const store = useShoppinglistItemStore();

const store = useShoppinglistDetailsViewStore();

const form = ref<ResquestNewShoppinglistItem>({
  ...defaultRequestNewShoppinglistItem,
});

const emit = defineEmits([
  "updateModalOpenValue",
  "updateShoppinglistItemList",
  "updateTotalPrice",
]);

const productSelectorList = ref<Product[]>([]);
const productSelected = ref<Product | null>(null);

const calculateSystemSelectorList = ref<CalculateSystem[]>([
  { ...defaultCalculateSystem },
]);
const calculateSystemSelected = ref<number>(-1);

const unitaryPriceResume = ref<number>(-1);
const quantityPriceResume = ref<number>(-1);
const weightResume = ref<number>(-1);
const priceKgResume = ref<number>(-1);

onMounted(async () => {
  productSelectorList.value = await getAllProductList();
  calculateSystemSelectorList.value = await getAllCalculateSystems();
});

watch(form.value.createItemUnitData.createWpItemUnitData, (newWp) => {
  weightResume.value = newWp.weight;
  priceKgResume.value = newWp.priceKg;
});

watch(form.value.createItemUnitData.createUpItemUnitData, (newUp) => {
  unitaryPriceResume.value = newUp.unitaryPrice;
  quantityPriceResume.value = newUp.quantity;
});

watch(productSelected, (newProductSelected) => {
  if (form.value && newProductSelected) {
    form.value.productInfo.productName = newProductSelected.name;
  }
});

watch(calculateSystemSelected, (newCalculateSystemSelected) => {
  if (form.value && newCalculateSystemSelected) {
    form.value.selectedCalculateSystem = newCalculateSystemSelected;
  }
});

function closeModal() {
  emit("updateModalOpenValue");
}

async function addShoppinglistItem() {
  /* let createItemUnit : boolean = ((form.value.selectedCalculateSystem === 1 &&
      form.value.createItemUnitData.createUpItemUnitData.quantity !== -1 &&
      form.value.createItemUnitData.createUpItemUnitData.unitaryPrice) ||
    (form.value.selectedCalculateSystem === 2 &&
      form.value.createItemUnitData.createWpItemUnitData.priceKg !== -1 &&
      form.value.createItemUnitData.createWpItemUnitData.weight !== -1)); */
  let createItemUnit: boolean = true; // TODO: ASIGNAR LOGICA DE ARRIBA
  form.value.createItemUnitData.createItemUnit = createItemUnit;
  let response: ResponseNewShoppinglistItem = await createShoppinglistItem(
    form.value,
    Number(route.params.id)
  );
  if (response.created) {
    store.addShoppinglistItemMetadata({
      id: response.idShoppinglistItemCreated,
      assignationToListDate: response.creationDate,
      name:
        form.value.productInfo.productName !== undefined ||
        form.value.productInfo.productName === ""
          ? form.value.productInfo.productName
          : "Macarrones",
      calculateSystemCode:
        form.value.selectedCalculateSystem === 1 ? "UP" : "WP",
      calculatedPrice: response.shoppinglistItemCalculatedPrice,
    });
    // EN ESTE PUNTO SE DEBEN DE LANZAR LOS 2 NUEVOS ENDPOINTS
    // -> addShoppinglistItem
    // -> updateTotalPrice
    await addShoppinglistItemToShoppinglist(
      Number(route.params.id),
      response.idShoppinglistItemCreated
    );
    await updateShoppinglistTotalPrice(Number(route.params.id));
    // Al lanzar la funcion closeModal al crear un shopping list item no se actualiza y aparece automaticamente :()
    // emit("updateShoppinglistItemList");
    // emit("updateTotalPrice"); // El problema viene de esta llamada
    store.setTotalPrice(store.shoppinglistDetailsViewItems);
    closeModal();
  }
}

function verifyAddItemForm(): boolean {
  // TODO: Añadir verificaciones para el formulario
  return false;
}

function calculatePrice(
  calculateSystemSelected: number,
  priceKg: number,
  weight: number,
  unitaryPrice: number,
  quantity: number
): number {
  if (calculateSystemSelected === -1) {
    return 0;
  } else {
    return calculateSystemSelected === 1
      ? calculateShoppinglistItemUpPrice(unitaryPrice, quantity)
      : calculateShoppinglistItemWpPrice(priceKg, weight);
  }
}

function calculateShoppinglistItemWpPrice(
  priceKg: number,
  weight: number
): number {
  return priceKg * weight;
}

function calculateShoppinglistItemUpPrice(
  unitaryPrice: number,
  quantity: number
): number {
  return unitaryPrice * quantity;
}
</script>
<template>
  <IonModal :is-open="openModal">
    <IonHeader>
      <IonToolbar>
        <IonTitle class="custom-text-header">Nuevo Item</IonTitle>
        <IonButtons slot="end">
          <IonButton
            @click="closeModal"
            style="font-style: italic; font-size: large"
            >Cerrar</IonButton
          >
        </IonButtons>
      </IonToolbar>
    </IonHeader>
    <IonContent>
      <IonCard>
        <IonCardHeader>
          <IonCardTitle>Creación del item</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
          <!-- SELECTOR DE PRODUCTO -->
          <IonList>
            <IonItem>
              <IonSelect
                v-model="productSelected"
                aria-label="Productos"
                placeholder="Selecciona un producto"
              >
                <IonSelectOption
                  v-for="element in productSelectorList"
                  :key="element.id"
                  :value="element"
                >
                  {{ element.name }}</IonSelectOption
                >
              </IonSelect>
            </IonItem>
          </IonList>
          <IonInput
            label="Nombre"
            label-placement="floating"
            placeholder="Nombre producto"
            v-model="form.productInfo.productName"
            :disabled="
              form.productInfo.productName !== '' && productSelected !== null
            "
          ></IonInput>
          <!-- SELECTOR DE CALCULATE SYSTEM -->
          <IonList>
            <IonItem>
              <IonSelect
                v-model="calculateSystemSelected"
                aria-label="Calculo de precio"
                placeholder="Selecciona el sistema de calculo"
              >
                <IonSelectOption
                  v-for="(element, index) in calculateSystemSelectorList"
                  :key="index"
                  :value="element.id"
                  >{{ element.name }}</IonSelectOption
                >
              </IonSelect>
            </IonItem>
          </IonList>
          <!-- PRECIO UNITARIO -->
          <div v-if="calculateSystemSelected === 1">
            <IonInput
              label="Precio Unitario"
              label-placement="floating"
              placeholder="Precio unitario"
              v-model="
                form.createItemUnitData.createUpItemUnitData.unitaryPrice
              "
              type="number"
            ></IonInput>
            <IonInput
              label="Cantidad"
              label-placement="floating"
              placeholder="Cantidad"
              v-model="form.createItemUnitData.createUpItemUnitData.quantity"
              type="number"
            ></IonInput>
          </div>
          <!-- PRECIO WEIGHT -->
          <div v-if="calculateSystemSelected === 2">
            <IonInput
              label="Peso"
              label-placement="floating"
              placeholder="Peso"
              v-model="form.createItemUnitData.createWpItemUnitData.weight"
              type="number"
            ></IonInput>
            <IonInput
              label="Precio Kg"
              label-placement="floating"
              placeholder="Precio Kg"
              v-model="form.createItemUnitData.createWpItemUnitData.priceKg"
              type="number"
            ></IonInput>
          </div>
        </IonCardContent>
      </IonCard>
      <IonCard style="background-color: rgb(242, 252, 230)">
        <IonCardHeader>
          <IonCardTitle>Resumen</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
          <div v-if="form.productInfo.productName !== ''">
            <IonChip color="primary"
              >Producto: {{ form.productInfo.productName }}</IonChip
            >
          </div>
          <div v-if="form.selectedCalculateSystem !== -1">
            <IonChip color="warning"
              >Sistema de calculo:
              {{ form.selectedCalculateSystem === 1 ? "UP" : "WP" }}</IonChip
            >
            <div v-if="form.selectedCalculateSystem === 1">
              <IonChip color="tertiary"
                >Precio Unitario: {{ unitaryPriceResume }}</IonChip
              >
              <IonChip color="tertiary"
                >Cantidad: {{ quantityPriceResume }}</IonChip
              >
            </div>
            <div v-if="form.selectedCalculateSystem === 2">
              <IonChip color="tertiary"
                >Peso:
                {{ weightResume }}
                Kg</IonChip
              >
              <IonChip color="tertiary">Kg/€: {{ priceKgResume }}</IonChip>
            </div>
          </div>
          <div>
            <IonChip color="danger"
              >Precio del item:
              {{
                calculatePrice(
                  form.selectedCalculateSystem,
                  priceKgResume,
                  weightResume,
                  unitaryPriceResume,
                  quantityPriceResume
                )
              }}</IonChip
            >
          </div>
          <IonButton
            style="margin-left: 10px; margin-right: 10px"
            expand="block"
            @click="addShoppinglistItem()"
            :disabled="verifyAddItemForm()"
            >Añadir</IonButton
          >
        </IonCardContent>
      </IonCard>
    </IonContent>
  </IonModal>
</template>
<style lang="css">
.custom-text-header {
  text-align: center;
  font-size: large;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-style: italic;
  padding: 2rem;
}
ion-toolbar::part(container) {
  background-color: rgb(51, 255, 153);
}
</style>
