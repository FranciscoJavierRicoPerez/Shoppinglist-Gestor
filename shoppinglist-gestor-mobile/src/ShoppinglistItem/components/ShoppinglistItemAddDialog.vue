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
const route = useRoute();
const { refetch: getAllProductList } = useGetAllProducts();
const { refetch: createShoppinglistItem } = useCreateShoppinglistItem();
defineProps({
  openModal: {
    type: Boolean,
  },
});

const form = ref<ResquestNewShoppinglistItem>({
  ...defaultRequestNewShoppinglistItem,
});

const emit = defineEmits(["updateModalOpenValue"]);

const productSelectorList = ref<Product[]>([]);
const productSelected = ref<Product | null>(null);

const calculateSystemSelectorList = ref<string[]>(["UP", "WP"]);
const calculateSystemSelected = ref<string>("");

onMounted(async () => {
  productSelectorList.value = await getAllProductList();
});

watch(productSelected, (newProductSelected) => {
  if (form.value && newProductSelected) {
    form.value.requestProduct.productId = newProductSelected.id
    form.value.requestProduct.name = newProductSelected.name;
  }
});

watch(calculateSystemSelected, (newCalculateSystemSelected) => {
  if (form.value && newCalculateSystemSelected) {
    form.value.calculateSystem = newCalculateSystemSelected;
  }
});

function closeModal() {
  emit("updateModalOpenValue");
}

async function addShoppinglistItem() {
  console.log("INFO: Adding new shoppinglist item");
  console.log(form.value);
  form.value.shoppinglistId = Number(route.params.id)
  await createShoppinglistItem(form.value);
}

function verifyAddItemForm(): boolean {
  // TODO: Añadir verificaciones para el formulario
  return false;
}
</script>
<template>
  <IonModal :is-open="openModal">
    <IonHeader>
      <IonToolbar>
        <IonTitle class="custom-text-header">Añadir Item</IonTitle>
        <IonButtons slot="end">
          <IonButton @click="closeModal()">Cerrar</IonButton>
        </IonButtons>
      </IonToolbar>
    </IonHeader>
    <IonContent>
      <IonCard>
        <IonCardHeader>
          <IonCardTitle>Producto</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
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
            v-model="form.requestProduct.name"
            :disabled="
              form.requestProduct.name !== '' && productSelected !== null
            "
          ></IonInput>
        </IonCardContent>
      </IonCard>
      <IonCard>
        <IonCardHeader>
          <IonCardTitle>Item</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
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
                  :value="element"
                  >{{ element }}</IonSelectOption
                >
              </IonSelect>
            </IonItem>
          </IonList>
        </IonCardContent>
      </IonCard>
      <!-- ION CARD PARA EL CALCULO DE LOS ITEMS EN PRECIO UNITARIO -->
      <IonCard
        v-if="
          calculateSystemSelected !== '' &&
          calculateSystemSelected === 'UP'
        "
      >
        <IonCardHeader>
          <IonCardTitle>Precio Unitario</IonCardTitle>
        </IonCardHeader>
        <IonCardContent>
          <IonInput
            label="Precio"
            label-placement="floating"
            placeholder="Precio del producto"
            v-model="form.unitaryPrice"
            type="number"
          ></IonInput>
        </IonCardContent>
      </IonCard>
      <!-- ********************************************************** -->
      <!-- ION CARD PARA EL CALCULO DE LOS ITEM EN KG/€-->
      <!-- ********************************************************** -->
      <IonButton
        style="margin-left: 10px; margin-right: 10px"
        expand="block"
        @click="addShoppinglistItem()"
        :disabled="verifyAddItemForm()"
        >Añadir</IonButton
      >
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
