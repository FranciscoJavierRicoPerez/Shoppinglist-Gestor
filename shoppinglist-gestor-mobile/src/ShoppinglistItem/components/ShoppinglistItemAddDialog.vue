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
} from "../infrastructure/models/RequestNewShoppinglistItem";
import { Product } from "@/Product/domain/Product";
import { useGetAllProducts } from "@/Product/application/useGetAllProducts";

const { refetch: getAllProductList } = useGetAllProducts();
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

onMounted(async () => {
  productSelectorList.value = await getAllProductList();
});

watch(productSelected, (newProductSelected) => {
  if (form.value && newProductSelected) {
    form.value.requestProduct.name = newProductSelected.name;
  }
});

function closeModal() {
  emit("updateModalOpenValue");
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
          ></IonInput>
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
