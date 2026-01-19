<script setup lang="ts">
import ProductsSelector from './ProductsSelector.vue'
import InputText from 'primevue/inputtext'
import FloatLabel from 'primevue/floatlabel'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()
</script>
<template>
  <div class="flex flex-column gap-2">
    <ProductsSelector></ProductsSelector>
    <FloatLabel variant="on">
      <InputText
        id="productName"
        v-model="createShoppinglistItemFormStore.selectedProduct"
        class="w-full"
      ></InputText>
      <label for="productName">Producto</label>
    </FloatLabel>
  </div>
</template>
<style lang="css"></style>
