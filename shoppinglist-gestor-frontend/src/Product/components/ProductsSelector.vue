<script setup lang="ts">
import Select from 'primevue/select'
import { onMounted, ref, watch } from 'vue'
import { useGetAllProductsNames } from '@/Product/application/useGetAllProductsNames'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'

// 1º - Crear un caso de uso en el que se devuelvan todos los productos en bbdd
const { refetch: getAllProductsNames } = useGetAllProductsNames()

const selectedProduct = ref<string>()
const optionsProducts = ref<string[]>()

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()

watch(selectedProduct, (newSelectedProduct) => {
  newSelectedProduct ? (createShoppinglistItemFormStore.selectedProduct = newSelectedProduct) : ''
})

onMounted(async () => {
  const response = await getAllProductsNames()
  optionsProducts.value = response.productsNames
  // Opcion para validar si el producto se ha introducido a traves del selector o en el input.
  // -> Aqui almaceno en el store del formulario de creación el listado de opciones,
  // asi puedo usarlo para comparar con lo que hay en el valor selectedProduct del mismo store
  // si ese valor no coincidie con ninguno de la lista entonces es un producto nuevo
  createShoppinglistItemFormStore.productOptions = response.productsNames
})
</script>
<template>
  <!-- <Select
    style="min-width: 100%"
    v-model="selectedProduct"
    :options="optionsProducts"
    placeholder="Selecciona un Producto"
    :disabled="
      createShoppinglistItemFormStore.verifyIsSelectedProductAlreadyExists(
        createShoppinglistItemFormStore.selectedProduct,
        createShoppinglistItemFormStore.productOptions,
      )
    "
  ></Select> -->
  <Select
    style="min-width: 100%"
    v-model="selectedProduct"
    :options="optionsProducts"
    placeholder="Selecciona un Producto"
  ></Select>
</template>
<style lang="css"></style>
