<script setup lang="ts">
import { onMounted, ref } from "vue";
import { Product } from "@/Product/domain/Product";
import { useGetAllProducts } from "@/Product/application/useGetAllProducts";
import { IonItem, IonList, IonSelect, IonSelectOption } from "@ionic/vue";

const { refetch: getAllProductList } = useGetAllProducts();
const productSelectorList = ref<Product[]>([]);

onMounted(async () => {
  productSelectorList.value = await getAllProductList();
});
</script>
<template>
  <IonList>
    <IonItem>
      <IonSelect aria-label="Productos" placeholder="Selecciona un producto">
        <IonSelectOption v-for="element in productSelectorList">{{
          element.name
        }}</IonSelectOption>
      </IonSelect>
    </IonItem>
  </IonList>
</template>
<style lang="css"></style>
