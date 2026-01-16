<script setup lang="ts">
import ProductsSelector from './ProductsSelector.vue'
import InputText from 'primevue/inputtext'
import FloatLabel from 'primevue/floatlabel'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()
</script>
<template>
  <ProductsSelector></ProductsSelector>
  <FloatLabel variant="on" style="margin-top: 1rem">
    <InputText
      id="productName"
      v-model="createShoppinglistItemFormStore.selectedProduct"
      style="min-width: 100%"
    ></InputText>
    <label for="productName">Producto</label>
  </FloatLabel>
</template>
<style lang="css"></style>
